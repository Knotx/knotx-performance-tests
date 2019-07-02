#!/usr/bin/env bash

# Start DB for Dockbix - default 1GB innodb_buffer_pool_size is used
docker run \
	-d \
	--name dockbix-db \
	-p 3306:3306 \
  --mount src="$(pwd)/localtime",target=/etc/localtime:ro,type=bind \
	--env="MARIADB_USER=zabbix" \
	--env="MARIADB_PASS=my_password" \
	monitoringartist/zabbix-db-mariadb

# Start Dockbix linked to the started DB
docker run \
    -d \
    --name dockbix \
    -p 8099:80 \
    -p 10051:10051 \
    --mount src="$(pwd)/localtime",target=/etc/localtime:ro,type=bind \
    --link dockbix-db:dockbix.db \
    --env="ZS_DBHost=dockbix.db" \
    --env="ZS_DBUser=zabbix" \
    --env="ZS_DBPassword=my_password" \
    --env="XXL_zapix=true" \
    --env="XXL_grapher=true" \
    --env="ZJ_enabled=true" \
    --env="ZS_StartJavaPollers=3" \
    monitoringartist/dockbix-xxl:latest
# Wait ~30 seconds for Zabbix initialization
# Zabbix web will be available on the port 8099, Zabbix server on the port 10051
# Default credentials: Admin/zabbix