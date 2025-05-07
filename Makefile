# Configuration variables
CONTAINER_NAME := mysql-container
MYSQL_USER := clinica_user
MYSQL_PASSWORD := clinica_password
MYSQL_DATABASE := clinica
MYSQL_ROOT_PASSWORD := root_password

# Colors for messages
GREEN := \033[0;32m
NC := \033[0m # No Color

# Default rule (help)
.PHONY: help
help:
	@echo "$(GREEN)Makefile for managing MySQL in an LXC container$(NC)"
	@echo ""
	@echo "Usage:"
	@echo "  make help            - Displays this help message"
	@echo "  make create          - Creates the LXC container"
	@echo "  make install         - Installs MySQL in the container"
	@echo "  make configure       - Configures MySQL for external access"
	@echo "  make create-db       - Creates the database and user"
	@echo "  make initialize      - Loads clinica.sql into the database"
	@echo "  make all             - Executes the full setup (create, install, configure, create-db, initialize)"
	@echo "  make clean           - Removes the database and user"
	@echo "  make fclean          - Completely removes the container"
	@echo "  make shell           - Accesses the container shell"
	@echo "  make mysql           - Connects to MySQL as root"
	@echo "  make info            - Displays connection information"
	@echo ""

# Create the LXC container
.PHONY: create
create:
	@echo "$(GREEN)Creating LXC container $(CONTAINER_NAME)...$(NC)"
	@if lxc info $(CONTAINER_NAME) >/dev/null 2>&1; then \
		echo "The container $(CONTAINER_NAME) already exists."; \
	else \
		lxc launch ubuntu:20.04 $(CONTAINER_NAME); \
		echo "Waiting for the container to be ready..."; \
		sleep 10; \
	fi

# Install MySQL in the container
.PHONY: install
install: create
	@echo "$(GREEN)Installing MySQL in the container...$(NC)"
	@lxc exec $(CONTAINER_NAME) -- bash -c "\
		apt-get update && \
		apt-get install -y mysql-server && \
		systemctl enable mysql && \
		systemctl start mysql"

# Configure MySQL for external access
.PHONY: configure
configure: install
	@echo "$(GREEN)Configuring MySQL for external access...$(NC)"
	@lxc exec $(CONTAINER_NAME) -- bash -c "\
		sed -i 's/127.0.0.1/0.0.0.0/' /etc/mysql/mysql.conf.d/mysqld.cnf && \
		systemctl restart mysql"
	@lxc exec $(CONTAINER_NAME) -- bash -c "\
		mysql -u root -e \"ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '$(MYSQL_ROOT_PASSWORD)';\" && \
		mysql -u root -p$(MYSQL_ROOT_PASSWORD) -e \"FLUSH PRIVILEGES;\""

# Create the database and user
.PHONY: create-db
create-db: configure
	@echo "$(GREEN)Creating database and user...$(NC)"
	@lxc exec $(CONTAINER_NAME) -- bash -c "\
		mysql -u root -p$(MYSQL_ROOT_PASSWORD) -e \"CREATE DATABASE IF NOT EXISTS $(MYSQL_DATABASE);\" && \
		mysql -u root -p$(MYSQL_ROOT_PASSWORD) -e \"CREATE USER IF NOT EXISTS '$(MYSQL_USER)'@'%' IDENTIFIED BY '$(MYSQL_PASSWORD)';\" && \
		mysql -u root -p$(MYSQL_ROOT_PASSWORD) -e \"GRANT ALL PRIVILEGES ON $(MYSQL_DATABASE).* TO '$(MYSQL_USER)'@'%';\" && \
		mysql -u root -p$(MYSQL_ROOT_PASSWORD) -e \"FLUSH PRIVILEGES;\""

# Load clinica.sql into the database
.PHONY: initialize
initialize: create-db
	@echo "$(GREEN)Initializing the database with clinica.sql...$(NC)"
	@lxc file push clinica.sql $(CONTAINER_NAME)/root/clinica.sql
	@lxc exec $(CONTAINER_NAME) -- bash -c "\
		mysql -u root -p$(MYSQL_ROOT_PASSWORD) $(MYSQL_DATABASE) < /root/clinica.sql"
	@echo "$(GREEN)Database initialized successfully.$(NC)"

# Run the full setup
.PHONY: all
all: create install configure create-db initialize

# Remove the database and user
.PHONY: clean
clean:
	@echo "$(GREEN)Removing database and user...$(NC)"
	@if lxc info $(CONTAINER_NAME) >/dev/null 2>&1; then \
		lxc exec $(CONTAINER_NAME) -- bash -c "\
			mysql -u root -p$(MYSQL_ROOT_PASSWORD) -e \"DROP DATABASE IF EXISTS $(MYSQL_DATABASE);\" && \
			mysql -u root -p$(MYSQL_ROOT_PASSWORD) -e \"DROP USER IF EXISTS '$(MYSQL_USER)'@'%';\"" && \
		echo "Database and user removed successfully."; \
	else \
		echo "The container does not exist."; \
	fi

# Completely remove the container
.PHONY: fclean
fclean:
	@echo "$(GREEN)Completely removing the container $(CONTAINER_NAME)...$(NC)"
	@if lxc info $(CONTAINER_NAME) >/dev/null 2>&1; then \
		lxc stop $(CONTAINER_NAME) --force; \
		lxc delete $(CONTAINER_NAME); \
		echo "Container removed successfully."; \
	else \
		echo "The container does not exist."; \
	fi

# Access the container shell
.PHONY: shell
shell:
	@echo "$(GREEN)Accessing the container $(CONTAINER_NAME)...$(NC)"
	@lxc exec $(CONTAINER_NAME) -- bash

# Connect to MySQL as root
.PHONY: mysql
mysql:
	@echo "$(GREEN)Connecting to MySQL as root...$(NC)"
	@lxc exec $(CONTAINER_NAME) -- mysql -u root -p$(MYSQL_ROOT_PASSWORD)

# Display connection information
.PHONY: info
info:
	@echo "$(GREEN)=================================================$(NC)"
	@echo "$(GREEN)         CONNECTION INFORMATION                     $(NC)"
	@echo "$(GREEN)=================================================$(NC)"
	@if lxc info $(CONTAINER_NAME) >/dev/null 2>&1; then \
		IP=$$(lxc exec $(CONTAINER_NAME) -- ip -4 addr show eth0 | grep -oP '(?<=inet\s)\d+(\.\d+){3}') && \
		echo "$(GREEN)Container IP:$(NC) $$IP" && \
		echo "$(GREEN)Port:$(NC) 3306" && \
		echo "$(GREEN)Database:$(NC) $(MYSQL_DATABASE)" && \
		echo "$(GREEN)User:$(NC) $(MYSQL_USER)" && \
		echo "$(GREEN)Password:$(NC) $(MYSQL_PASSWORD)" && \
		echo "" && \
		echo "$(GREEN)To connect using MySQL CLI:$(NC)" && \
		echo "  mysql -h $$IP -P 3306 -u $(MYSQL_USER) -p$(MYSQL_PASSWORD) $(MYSQL_DATABASE)" && \
		echo "" && \
		echo "$(GREEN)To connect using JDBC:$(NC)" && \
		echo "  jdbc:mysql://$$IP:3306/$(MYSQL_DATABASE)"; \
	else \
		echo "The container does not exist."; \
	fi
