/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
/* Example for use of MaxBotix MB1043 Ultrasonic Sensor with an FRC Robot     */
/* Based on WPILib Timed Robot Skeleton template.                             */
/* Modified by: Jordan Hofstrand, MaxBotix Inc.                               */
/* Last modified: 10-04-2021                                                  */
/* For questions please contact (218)-454-7363 or techsupport@maxbotix.com    */
/* Datasheet and other information avaliable at www.MaxBotix.com/firstrobotics*/
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;

public class Robot extends TimedRobot {

  public DigitalOutput ultrasonicTriggerPinOne = new DigitalOutput(0);
  public DigitalOutput ultrasonicTriggerPinTwo = new DigitalOutput(1);

  public AnalogInput ultrasonicSensorOne = new AnalogInput(0);
  public AnalogInput ultrasonicSensorTwo = new AnalogInput(1);

  public double ultrasonicSensorOneRange = 0;
  public double ultrasonicSensorTwoRange = 0;

  public double voltageScaleFactor = 1;

  public void turnOnSensorOne() {
    ultrasonicTriggerPinOne.set(true);
    ultrasonicTriggerPinTwo.set(false);
  }

  public void turnOnSensorTwo() {
    ultrasonicTriggerPinOne.set(false);
    ultrasonicTriggerPinTwo.set(true);
  }

  public void turnOffBothSensors() {
    ultrasonicTriggerPinOne.set(false);
    ultrasonicTriggerPinTwo.set(false);
  }

  @Override
  public void robotInit() {
    //Initialize range readings on SmartDashboard as max distance in Centimeters.
    SmartDashboard.putNumber("Sensor 1 Range", 500);
    SmartDashboard.putNumber("Sensor 2 Range", 500);
  }
  @Override
  public void robotPeriodic() {
    //Publish range readings to SmartDashboard
    SmartDashboard.putNumber("Sensor 1 Range", ultrasonicSensorOneRange);
    SmartDashboard.putNumber("Sensor 2 Range", ultrasonicSensorTwoRange);

    voltageScaleFactor = 5/RobotController.getVoltage5V(); //Calculate what percentage of 5 Volts we are actually at
  }
  @Override
  public void autonomousInit() {
    //If we are in Autonomous mode, turn on the first sensor (and turn off the second sensor)
    turnOnSensorOne();
  }
  @Override
  public void autonomousPeriodic() {
    //Get a reading from the first sensor, scale it by the voltageScaleFactor, and then scale to Centimeters
    ultrasonicSensorOneRange = ultrasonicSensorOne.getValue()*voltageScaleFactor*0.125;

  }
  @Override
  public void teleopInit() {
    //If we are in Teleoperated mode, turn on the second sensor (and turn off the first sensor)
    turnOnSensorTwo();
  }

  @Override
  public void teleopPeriodic() {
    //Get a reading from the second sensor, scale it by the voltageScaleFactor, and then scale to Centimeters
    ultrasonicSensorTwoRange = ultrasonicSensorTwo.getValue()*voltageScaleFactor*0.125;
  }

  @Override
  public void disabledInit() {
    //If the robot is disabled, turn off both sensors
    turnOffBothSensors();
  }
}
