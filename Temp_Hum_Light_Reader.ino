#include <dht.h>
dht DHT;
#define DHT11_PIN 2

void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
}

void loop() {

  // check/activate dht sensor
  int check = DHT.read11(DHT11_PIN);

  // check dht sensor for temperature in C and convert to F
  int tempValue = DHT.temperature;
  tempValue = (((tempValue * 9) / 5) + 32);

  // check dht sensor for relative humidity
  int humValue = DHT.humidity;

  // read value (ADC) of light from photo resistor
  int lightValue = analogRead(A5);

  // create a text array and format the output
  char text[40];
  sprintf(text, "%d,%d,%d\n", lightValue, tempValue, humValue);
  Serial.println(text);
  // Serial.println(lightValue); For single data stream use
  
  delay(10);        // delay in between reads for stability
}
