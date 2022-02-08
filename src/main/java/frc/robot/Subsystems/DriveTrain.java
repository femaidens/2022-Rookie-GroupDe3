// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import frc.robot.RobotMap;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Subsystems.DriveTrain;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.OI;

/** Add your docs here. */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static CANSparkMax frontLeft = new CANSparkMax(RobotMap.frontLeftPort, MotorType.kBrushless);
	public static CANSparkMax frontRight = new CANSparkMax(RobotMap.frontRightPort, MotorType.kBrushless);
	public static CANSparkMax rearLeft = new CANSparkMax(RobotMap.rearLeftPort, MotorType.kBrushless);
	public static CANSparkMax rearRight = new CANSparkMax(RobotMap.rearRightPort, MotorType.kBrushless);

  public static RelativeEncoder leftEncoder = frontLeft.getEncoder();
  public static RelativeEncoder rightEncoder = frontRight.getEncoder();

	public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);

	public MecanumDrive mecanum = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

  public static double margin = 0.01;

  public void turnDegrees(double degrees){
    degrees -= gyro.getAngle(); 
    //ex: if robot is already at a certain degree (told by getGyro), then subtracting -> degrees that robot will need to turn
    if (degrees > 360) {
      degrees -= 360;
    } else if (degrees < 0) {
      degrees += 360;
    }
    if (degrees <= 180 && degrees >=0) { //robot constantly turns to the right
      while (gyro.getAngle() < degrees) {
        mecanum.driveCartesian(0, 0, 0.5);
      }
    } else if (degrees > 180) {  
        mecanum.driveCartesian(0, 0, -0.5);
      while (gyro.getAngle() > degrees) { //robot constantly turns to the left
        mecanum.driveCartesian(0, 0, -0.5);
      }
    }
  }

	public void driveTeleop(){
		mecanum.driveCartesian(OI.rightJoy.getRawAxis(RobotMap.rightJoyYPort), OI.rightJoy.getRawAxis(RobotMap.rightJoyXPort), OI.leftJoy.getRawAxis(RobotMap.leftJoyYPort), gyro.getAngle());
	}

  public void driveStraight(double x, double y, double z){
    mecanum.driveCartesian(y, x, z);
  }

  public void driveDistance(double ticks) {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
    while (leftEncoder.getPosition() > rightEncoder.getPosition() + margin || leftEncoder.getPosition()  > rightEncoder.getPosition() - margin) {
      mecanum.driveCartesian(0, 0.5, -0.05);
    }
    while (rightEncoder.getPosition() > leftEncoder.getPosition()  + margin || rightEncoder.getPosition() > leftEncoder.getPosition() - margin) {
      mecanum.driveCartesian(0, 0.5, 0.05);
    }
    mecanum.driveCartesian(0, 0.5, 0); //when all done, move robot along the x-axis according to magnitude
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
