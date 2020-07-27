/* Important!
   Add database_setup.sql to your .gitignore
   git add .gitignore
   Then as your superadmin or root user, run the file from the terminal command line
   mysql -p < database_setup.sql
 */

DROP DATABASE IF EXISTS team_adlister;

CREATE DATABASE IF NOT EXISTS team_adlister;

USE team_adlister;

CREATE USER 'team_adlister'@'localhost' IDENTIFIED BY 'codeup';
GRANT ALL ON team_adlister.* TO 'team_adlister'@'localhost';