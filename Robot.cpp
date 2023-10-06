// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*----------------------------------------------------------------------------*/
/* Example for use of MaxBotix MB1043 Ultrasonic Sensor with an FRC Robot     */
/* Based on WPILib Timed Robot Skeleton template.                             */
/* Modified by: Jordan Hofstrand, MaxBotix Inc.                               */
/* Last modified: 10-04-2021                                                  */
/* For questions please contact (218)-454-7363 or techsupport@maxbotix.com    */
/* Datasheet and other information avaliable at www.MaxBotix.com/firstrobotics*/
/*----------------------------------------------------------------------------*/

#include "Robot.h"
#include <frc/AnalogInput.h>
#include <frc/DigitalOutput.h>
#include <frc/smartdashboard/SmartDashboard.h>
#include "frc/RobotController.h"

frc::AnalogInput ultrasonic_sensor_one{0};
frc::AnalogInput ultrasonic_sensor_two{1};

frc::DigitalOutput ultrasonic_trigger_pin_one{0};
frc::DigitalOutput ultrasonic_trigger_pin_two{1};

double ultrasonic_sensor_range_one = 0;
double ultrasonic_sensor_range_two = 0;

double voltage_scale_factor = 0;

void TurnOnSensorOne() {
    ultrasonic_trigger_pin_one.Set(true);
    ultrasonic_trigger_pin_two.Set(false);
}

void TurnOnSensorTwo() {
    ultrasonic_trigger_pin_one.Set(false);
    ultrasonic_trigger_pin_two.Set(true);
}

void TurnOffBothSensors() {
    ultrasonic_trigger_pin_one.Set(false);
    ultrasonic_trigger_pin_two.Set(false);
}

void Robot::RobotInit() {
  frc::SmartDashboard::PutNumber("Sensor 1 Range", 0);
  frc::SmartDashboard::PutNumber("Sensor 2 Range", 0);
  TurnOffBothSensors();
}
void Robot::RobotPeriodic() {
  frc::SmartDashboard::PutNumber("Sensor 1 Range", ultrasonic_sensor_range_one);
  frc::SmartDashboard::PutNumber("Sensor 2 Range", ultrasonic_sensor_range_two);

  voltage_scale_factor = 5/frc::RobotController::GetVoltage5V();
}

void Robot::AutonomousInit() {
  TurnOnSensorOne();
}
void Robot::AutonomousPeriodic() {
  ultrasonic_sensor_range_one = ultrasonic_sensor_one.GetValue() * voltage_scale_factor * 0.125;
}

void Robot::TeleopInit() {
  TurnOnSensorTwo();
}
void Robot::TeleopPeriodic() {
  ultrasonic_sensor_range_two = ultrasonic_sensor_two.GetValue() * voltage_scale_factor * 0.125;
}

void Robot::DisabledInit() {
  TurnOffBothSensors();
}
void Robot::DisabledPeriodic() {}

void Robot::TestInit() {}
void Robot::TestPeriodic() {}

#ifndef RUNNING_FRC_TESTS
int main() {
  return frc::StartRobot<Robot>();
}
#endif
