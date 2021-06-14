# Getting Started

## Requirements

This application needs docker and docker-compose, Java version 8 and above.

### Run the application | Production

If you wish to run the application simply run the deploy script, you can see the script for more details on what it does [here](deploy.sh)

```./deploy.sh```

If you need to add run permission use the command below

```chmod a+x deploy.sh```

### Debugging the application | Development

If you would like to debug the application you need to run the dev compose stack

```docker-compose -f docker-compose-dev.yml up```

The dev stack runs mysql and exposes its ports to the host machine, it also has an adminer instance. Helps manage the database.

Once the stack is running you can launch the debugger.

---

## Playing the game

The game can be played via curl or any tool that can make http requests.

Use any of the above ways to start the application

Once the application is start ues the below commands to play against the computer

### cUrl commands

see [here](curl.md)

### Adding new users

Currently only way to add new users is by adding new entries to the [data.sql](src/main/resources/data.sql) script.
