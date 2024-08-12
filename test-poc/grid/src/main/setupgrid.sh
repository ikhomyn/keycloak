#!/bin/bash
selenium_server_path=../../selenium-server-4.23.0.jar
java -jar $selenium_server_path hub &
java -jar $selenium_server_path node --port 5555 &
java -jar $selenium_server_path node --port 5556 &
java -jar $selenium_server_path node --port 5557
