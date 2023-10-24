# Brackets Checking API 

The application checks the correctness of the placement of brackets in the text.
Brackets can be any pair of opening and closing characters, depending on the settings.

# System Requirements

Before you can run this application, make sure you meet the following prerequisites:

- Java 9 or higher
- Maven 3.6.3 or higher

# Getting Started

Follow these steps to get started with "Brackets Checking API":

## Installation and Configuration

1. **Unzip the Archive:**

   Unzip the archive using the appropriate method for your operating system. <br /><br />

2. **Configuration (if needed):**

   If necessary, configure the `src/main/resources/application.yml` file with the following parameters:
   <br /><br />
    - `server port`: The default is `8081`. The port on which the application will run.        
      <br />
   
    - `is-inner-brackets-useful`: The default is `true`. Determines whether to consider inner brackets as useful symbols. For
      example, `({[s]})` or `((s)(s))` will return `true` if this parameter is set to `true`, and `false` if set
      to `false`. Note that duplicate identical brackets like `((s))` will always return `false`. 
      <br /><br />

    - `regex`: The default is `[^a-zA-Z0-9а-яА-Я]`. Ensure that the format for specifying useful symbols is in the form of a regular expression or
      consecutive characters without spaces. If you need to include a space as a useful symbol, include a single space
      character in the list. The format should be `[^list_of_useful_characters]`.
      <br /><br />

    - `brackets`: Opening and closing characters one after the other, in a list format for the .yml file.
      The default is:     
      - `"("`
      - `")"`
      - `"["`
      - `"]"`
      - `"{"`
      - `"}"`
        <br /><br />

   ***Custom Logging Configuration***

    If you need custom logging settings, you can configure them in the `src/main/resources/log4j.properties` file.

    The logging application uses the `Log4j` library. By default, logging is directed to both the console and a log file located at `logs/brackets_checker.log`.


3. **Build the Application:**

   Execute the following command to build the application (make sure you are in the application directory):

   ```bash
   mvn clean install

4. **Run the Application:**

      ```bash
   java -jar target/brackets-checker-0.0.1-SNAPSHOT.jar
   
5. **Using the Application:**
   After launching the application, you can access its description and perform testing by visiting the following link:

   [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html), where "8081" is the port on which the application is running.
