//voltage_scale_factor allows us to compensate for differences in supply voltage.
double voltage_scale_factor = 5/RobotController.getVoltage5V();

//Formula to calculate range in Centimeters:
double currentDistanceCentimeters = raw_value * voltage_scale_factor * 0.125;

//Formula to calculate range in Inches:
double currentDistanceInches = raw_value * voltage_scale_factor * 0.0492;