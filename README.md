# (Java/Arduino) Light, Temperature, and Humidity Real-Time Graphing

## The Problem
My fiancé wants to start a garden in the backyard so that we can stop eating out at Chik-fil-A, Chili's, and Chipotle every day. I want to assist but I do not want to garden, so I decided to create a light, temperature, and humidity data interface that will optimize the watering and feeding cycle of the garden.

<b>EDIT: My fiancé informed me that this is too "try-hard" for a simple garden, I disagree, and that she will NOT be using my hardware/software... Now I have something to prove!</b>

## The Hardware

<i> For this project I decided to use Arduino-UNO and various low voltage sensors to make the device compact </i>

<br><b> Arduino UNO Microcontroller
 
 ![Arduino](https://user-images.githubusercontent.com/52724843/109441917-efac6a80-79f3-11eb-8e27-dfbb1e8069f5.jpg)
 
 DHT11 Temperature and Humidity Sensor 
 
 ![TempHumSensor](https://user-images.githubusercontent.com/52724843/109441704-3b124900-79f3-11eb-8552-fa420b1497f3.jpg)

 LDR Photoresistor
 
 ![LDRPhotoResistor](https://user-images.githubusercontent.com/52724843/109441918-f0dd9780-79f3-11eb-9240-8b9139d2599a.jpg)

 100k Ohm Resistor
 
 ![Resistor](https://user-images.githubusercontent.com/52724843/109441920-f20ec480-79f3-11eb-9b21-bca5ac6b1538.jpg) </b>
 
 ### Wiring Diagram
 
![wiringDiagram](https://user-images.githubusercontent.com/52724843/109442099-72352a00-79f4-11eb-91a4-60e2a9712bfe.PNG)


## The Software

Using Arduino's native language I was able to create a simple prgram that takes the input data from each sensor and display the results as serial text

<i>Testing the LDR Photoresistor with Arduino serial monitor</i><t>
 
![serialMonitor](https://user-images.githubusercontent.com/52724843/109443267-58e1ad00-79f7-11eb-9d21-92ab7725723b.gif)
As you can see, when light is applied to the sensor the ADC reading shoots up to the 650 range and when removed drops back down to around 400
