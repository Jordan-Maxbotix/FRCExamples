//Import required WPILib libraries
#include "frc/RobotController.h"
#include <frc/AnalogInput.h>

//Create an instance of the AnalogInput class so we can read from it later
frc::AnalogInput ultrasonic_sensor{0};

//voltage_scale_factor allows us to compensate for differences in supply voltage.
double voltage_scale_factor = 5/RobotController.getVoltage5V();

//Formula to calculate range in Centimeters:
double currentDistanceCentimeters = ultrasonic_sensor.GetValue() * voltage_scale_factor * 0.125;

//Formula to calculate range in Inches:
double currentDistanceInches = ultrasonic_sensor.GetValue() * voltage_scale_factor * 0.0492;
