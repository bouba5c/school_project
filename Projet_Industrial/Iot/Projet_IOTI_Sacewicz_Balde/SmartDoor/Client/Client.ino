#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include <Stepper.h>

#include <ESP8266WiFiMulti.h>
ESP8266WiFiMulti WiFiMulti;

const char* ssid = "Server-Project";
const char* password = "123456789";

//Your IP address or domain name with URL path
const char* serverNameBtn = "http://192.168.4.1/button";

#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>

unsigned long previousMillis = 0;
const long interval = 5000; 
int ENA = 4;
int IN1 = 0; 
int IN2 = 2;

int buttonState = 0;

void setup() {
  Serial.begin(9600);
  Serial.println();

  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("Connected to WiFi");
  
  pinMode(ENA, OUTPUT);
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
}

void loop() {
  
  unsigned long currentMillis = millis();
  if(currentMillis - previousMillis >= interval) {
     // Check WiFi connection status
    if ((WiFiMulti.run() == WL_CONNECTED)) {
      buttonState = httpGETRequest(serverNameBtn).toInt();

      if(buttonState == HIGH){
          digitalWrite(ENA, HIGH);
          digitalWrite(IN1, HIGH);
          digitalWrite(IN2, LOW);
      } else {
          digitalWrite(ENA, LOW);
          digitalWrite(IN1, LOW);
          digitalWrite(IN2, HIGH);
      }
      // save the last HTTP GET Request
      previousMillis = currentMillis;
    }
    else {
      Serial.println("WiFi Disconnected");
      exit(0);
    }
  }

}
String httpGETRequest(const char* serverName) {
  WiFiClient client;
  HTTPClient http;
    
  // Your IP address with path or Domain name with URL path 
  http.begin(client, serverName);
  
  // Send HTTP POST request
  int httpResponseCode = http.GET();
  
  String payload = "--"; 
  
  if (httpResponseCode>0) {
    Serial.print("HTTP Response code: ");
    Serial.println(httpResponseCode);
    payload = http.getString();
  }
  else {
    Serial.print("Error code: ");
    Serial.println(httpResponseCode);
  }
  // Free resources
  http.end();

  return payload;
}
