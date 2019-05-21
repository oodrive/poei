# Password manager service 

> Backend service to manage passwords

# Contract

You will need to develop the following HTTP APIs:

- API to generate a random password given some options
- API to verify a password complexity

## Random password generation

**HTTP Request (input)**

```
GET http://localhost:8080/password-manager/random?length=8&useChars=true&useDigits=true
```

Query parameters:

|Name|Type|Description|
|----|----|-----------|
|`length`|integer|The length of the generated password|
|`useChars`|boolean|Flag to use characters or not in the password generation|
|`useDigits`|boolean|Flag to use digits or not in the password generation|

**Expected HTTP Responses (output)**

- HTTP Status: 200
- Response body (JSON):

```json
{
  "value": "1yOn9FAhT"
}
```

## Password score

**HTTP Request (input)**

```
POST http://localhost:8080/password-manager/score
```

- Request body:

```json
{
  "value": "azerty"
}
```

**Expected HTTP Responses (output)**

- HTTP Status: 200
- Response body (JSON):

```json
{
  "score": "LOW"
}
```

For simplicity purpose, only 3 levels will be available:

- `LOW`
- `MEDIUM`
- `HIGH`

# Getting started
## Running the service with an IDE

Main class: `com.oodrive.poei.passwordmanager.PoeiPasswordManagerApplication`

## Running the service with Maven

```bash
./mvnw clean spring-boot:run
```

# Exercises
## 1 - Random password generator

**Goal**

> Expose a HTTP API to generate a random password.

The path to call the HTTP API must be the following:

```
GET http://localhost:8080/password-manager/random
```

The output of the HTTP request must be the following:

```json
{
  "value": "1yOn9FAhT"
}
```

**Instructions**

- Create Java class(es) to generate a random password
  - For the exercise, you will only use alpha numeric characters, no need for special characters
  - You can define arbitrarily the password length
  - :bulb: You can use the Java class `java.security.SecureRandom` to generate a random integer
- Create a controller that uses the Java class(es) to generate a random password

## 2 - Customizable password generator

**Goal**

> The client must be able to define the options to generate the password.

The path to call the HTTP API must be the following:

 ```
http://localhost:8080/password-manager/random?length=12&useChars=true&useDigits=true
 ```
 
The output of the HTTP request must be the following:

```json
{
  "value": "1yOn9FAhT"
}
```

**Instructions**

- Change your application so the following parameters can be customized:
  - the length of the password
  - password character types (letters and / or numbers)
- Update the controller in order to take into account the parameters provided in the request
 
## 3 - Password score computation

**Goal**

> Expose a HTTP API to check the password complexity.

The path to call the HTTP API must be the following:

```
POST http://localhost:8080/password-manager/score

Request body:

{
  "value": "azerty"
}
```

The output of the HTTP request must be the following:

```json
{
  "score": "LOW"
}
```

**Instructions**

- Create Java classes that compute the score of a given password
  - for simplicity purpose, the score will be divided in 3 levels: `LOW`, `MEDIUM` and `HIGH`
  - the computation of a password score will be up to you
- Update the controller to add a new endpoint that use the score computation
  - use the HTTP `POST` verb for this endpoint
  - :information_source: using HTTP `POST` method is better than HTTP `GET` method in this case as the password is in the request body instead of URL, which means the password in the request will be encrypted when in HTTPS

## 4 - Save passwords in the database

**Goal**

> Track the password given in the requests.

**Instructions**

- Modify the `pom.xml` file to use the dependencies `spring-boot-starter-jdbc` and `postgresql`
  - :warning: the application is already configured to use the database `password_manager`, so you may have to create the database beforehand
- Create a SQL table that will contain the passwords
- Create the Java DAO class that read and write lines in the database
- Update the Java classes so that at each verification, the password will be saved in the database
- Update the password complexity computation to include the following condition: "Has the password already been used?"
  - the condition can be read as: "Has the given password already been saved in the database?"

## 5 - Password hashing

**Goal**

> For security purpose, the passwords must not be saved in plain text readable by a human in the database.
> 
> One way is to store the password's hash in the database instead.
> 
> The hashing technique will always result the same output for a given input, in other words, for a given password value,
> its hash value will always be the same. So, it's also possible to check if a password has already been used or not.
> 
> The type of the hashes are in byte array, so we will use a mechanism to encode the hash into a printable characters
> using Base64 base encoding. This will ease the development to manipulate password hashes.
> Like the hashes, Base64 outputs are also always the same.

**Instructions**

- Create the Java classes that hash a given `String` and return the hashed `String` in Base64 format
  - you will use the algorithm `SHA256` to hash
    - :information_source: `SHA-256` is not the best hash algorithm for password as it is vulnerable to rainbow tables
      attacks, but it is suitable for this exercise as it is easy to develop
  - :bulb: use the standard Java class `java.util.Base64` to encode the hashed byte array into a `String`
- Update your code to save the password hashes in base64 format instead of plain password
- Update password complexity computation code to compare the password hashes instead of the password values

## 6 - Obfuscate passwords

**Goal**

> It's best practice to use `char[]` instead of `String` when manipulating passwords. Indeed, `String` are immutable,
> which means you can't manually obfuscate it by assigning a new `String` value as the old value will continue to live
> until the garbage collector passes.
>
> Example:
>
> ```java
> String password = "mysupersecret";
> password = "imtryingtoobfuscatethepassword";
> // the old value "mysupersecret" still lives in the heap of the java process
> ```
>
> Whereas `char[]` is not immutable, so it's possible to overwrite the content and the password value will no longer be
> present in the heap.
>
> Why is it important to obfuscate the password value after you're done with it?
>
> It's possible to obtain head dumps from Java application. Heap dumps are snapshots of the memory of the Java process,
> which means it's possible to read the password values in `String` in those heap dumps. In other words, if an attacker
> manages to fetch a heap dump of your Java application, the attacker will be able to fetch the passwords.

**Instructions**

- Update your Java classes to use `char[]` instead of `String` for the password value
- Update your Java classes to overwrite the value of the password values with ` ` characters after you're done with it and assign the password value to `null`

