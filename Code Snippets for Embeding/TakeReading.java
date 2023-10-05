//Calculate a scale factor for the range reading based on the RoboRIO's current 5V Rail voltage
//voltage_scale_factor allows us to compensate for differences in supply voltage
double voltage_scale_factor = 5/RobotController.getVoltage5V();

//Formula to calculate range in Centimeters:
double currentDistanceCentimeters = ultrasonicSensor.getValue() * voltage_scale_factor * 0.125;

//Formula to calculate range in Inches:
double currentDistanceInches = ultrasonicSensor.getValue() * voltage_scale_factor * 0.0492;