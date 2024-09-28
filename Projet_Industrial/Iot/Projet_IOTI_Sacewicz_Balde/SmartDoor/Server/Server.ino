// Import required libraries
#include <ESP8266WiFi.h>
#include "ESPAsyncWebServer.h"

#include <Wire.h>

int distance;
// Set your access point network credentials
const char* ssid = "Server-Project";
const char* password = "123456789";

// Create AsyncWebServer object on port 80
AsyncWebServer server(80);

String readButtonState() {
  if(Serial.available()){
    distance = Serial.read();
    if(distance < 9){
      return String(1);
    } else if(distance >= 9){
      return String(0);
    }
  }
}

void setup(){
  // Serial port for debugging purposes
  Serial.begin(9600);
  
  Serial.println();
  
  // Setting the ESP as an access point
  Serial.print("Setting AP (Access Point)…");
  // Remove the password parameter, if you want the AP (Access Point) to be open
  WiFi.softAP(ssid, password);
  
  IPAddress IP = WiFi.softAPIP();
  
  server.on("/button", HTTP_GET, [](AsyncWebServerRequest *request){
    request->send_P(200, "text/plain", readButtonState().c_str()); 
  });
  
  // Start server
  server.begin();
  Serial.println("Done");
}
 
void loop() {
  
}
