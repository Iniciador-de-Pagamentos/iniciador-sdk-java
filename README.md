# Iniciador Java SDK

Welcome to the Iniciador Java SDK! This tool is made for java developers who want to easily integrate with our API.

If you have no idea what Iniciador is, check out our [website](https://www.iniciador.com.br/)

## 1. Description

The Iniciador SDK is a Java library that provides a convenient way to interact with the Iniciador API.

## 2. Installation

To install the Iniciador SDK, add this line to your dependencies in build.gradle:

```sh
dependencies {
  implementation 'com.iniciador:sdk:0.0.1'
}
```

## 3. Usage

To use the Iniciador SDK, create an instance of the `Iniciador`:

```java
  public static void main(String[] args) {
    Iniciador iniciador = new Iniciador("c82700f8-f0bf-4cce-9068-a2fd6991ee9b",
                "sB#C8ybhJEN63RjBz6Kpd8NUywHkKzXN$d&Zr3j4", "dev");
  }
```

### 3.1 Whitelabel

#### 3.1.1 Authentication

To authenticate with the Iniciador Whitelabel, use the `authInterface` method:

```java
  public static void main(String[] args) {
    AuthInterfaceOutput authInterfaceOutput = iniciador.authInterface();

    String interfaceUrl = authInterfaceOutput.getInterfaceURL()
    String acessToken = authInterfaceOutput.getAccessToken()

  }
```

- Use interfaceURL to complete the payment flow
- Use the accessToken and paymentId to verify the payment data

#### 3.1.2 Payments

To use payments services with the Iniciador Whitelabel, use the `payments` method:

##### 3.1.2.1 `getPayment`

to get the payment details use `Get` method

```java
  public static void main(String[] args) {
    PaymentInitiationPayload payment = iniciador.getPayment(accessToken);
  }
```

##### 3.1.2.2 `status`

to get the payment status details use `getPaymentStatus` method

```java
  public static void main(String[] args) {
    PaymentStatusPayload payment = iniciador.getPaymentStatus(acessToken);
  }
```

### 3.2 API Only

#### 3.2.1 Authentication

To authenticate with the Iniciador API, use the `auth` method:

```java
  public static void main(String[] args) {
    AuthOutput authOutput = iniciador.auth();

    String acessToken = authOutput.getAccessToken()
  }
```

#### 3.2.2 Participants

To get participants with the Iniciador API, use the `participants` method:

```java
  public static void main(String[] args) {
    ParticipantFilterDto filter;

    ParticipantFilterOutputDto participants = iniciador.participants(accessToken, filter);
  }
```

#### 3.2.3 Payments

To use payments services with the Iniciador API, use the `payments` method:

##### 3.2.3.1 `send`

to send the payment use `sendPayment` method

```java
  public static void main(String[] args) {
    PaymentInitiationPayload payload;

    PaymentInitiationPayload payment = iniciador.sendPayment(accessToken, payload);
  }
```

##### 3.2.3.2 `get`

to get the payment details use `getPayment` method

```java
  public static void main(String[] args) {
    PaymentInitiationPayload payment = iniciador.getPayment(accessToken);
  }
```

##### 3.2.3.3 `status`

to get the payment status details use `getPaymentStatus` method

```java
  public static void main(String[] args) {
    PaymentStatusPayload payment = iniciador.getPaymentStatus(acessToken);
  }
```

## Help and Feedback

If you have any questions or need assistance regarding our SDK, please don't hesitate to reach out to us. Our dedicated support team is here to help you integrate with us as quickly as possible. We strive to provide prompt responses and excellent support.

We also highly appreciate any feedback you may have. Your thoughts and suggestions are valuable to us as we continuously improve our SDK and services. We welcome your input and encourage you to share your thoughts with us.

Feel free to contact us by sending an email to suporte@iniciador.com.br. We look forward to hearing from you and assisting you with your integration.
