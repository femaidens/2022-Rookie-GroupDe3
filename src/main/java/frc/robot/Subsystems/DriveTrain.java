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

  /*public void turnDegrees(double degrees){
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
        while (gyro.getAngle() > degrees) { 
        //robot constantly turns to the left; say wanted degrees is 275, thus robot turns left until its gyroPos is =/< 275
          mecanum.driveCartesian(0, 0, -0.5);
        }
    }
  } */

  public void driveC(double y, double x, double z) {
    mecanum.driveCartesian(z,x,y);
  }
  
  public void turnDegrees(double angle){
    if ((angle >= 180 && gyro.getAngle() >= 180) || (angle < 180 && gyro.getAngle() < 180)){
      //if angle on same side of circle as gyro
      while (gyro.getAngle() > angle){
        mecanum.driveCartesian(0.5, 0, -0.5);
      }
      while(gyro.getAngle() < angle){
        mecanum.driveCartesian(0.5, 0, 0.5);
      }
    }
    else if (angle > 180 && gyro.getAngle() <= 180){ //if angle on left side and gyro on right side
      if (gyro.getAngle() + (360-angle) < angle - gyro.getAngle()){ 
        //gyro.getAngle() + (360-angle) = outside angle; angle - gyro.getAngle() = inside angle
        while (gyro.getAngle() + 360 > angle){
          mecanum.driveCartesian(0.5, 0, -0.5);
        }
      }
      else if (gyro.getAngle() + (360-angle) > angle - gyro.getAngle()){ 
        while (gyro.getAngle() < angle){
          mecanum.driveCartesian(0.5, 0, 0.5);
        }
      }
    }
    else if (angle < 180 && gyro.getAngle() > 180){ //if angle on right side of circle and gyro on left side of circle
      if (gyro.getAngle() + (360-angle) < gyro.getAngle() - angle){ //gyro.getAngle() - angle = inside angle
        while(gyro.getAngle() < angle + 360){
          mecanum.driveCartesian(0.5, 0, -0.5);
        }
      }
      if (gyro.getAngle() + (360-angle) > gyro.getAngle() - angle){ //robot turns right to hit angle 
        while(gyro.getAngle() > angle){
          mecanum.driveCartesian(0.5, 0, 0.5);
        }
      }
    }

  }

  public void turnDegrees2(double angle) { //ISSUE: gyro = 200 ddeg, wanted angle = 359 deg
    if (angle > 180) { //ex: angle = 260; gyro angle = 60
      angle = 360 - angle; // Ex: angle = 359, thus 360 - 359 = 1 deg
      while (360 - gyro.getAngle() != angle) {
        if (360 - gyro.getAngle() > angle) { 
          mecanum.driveCartesian(-0.5, 0, 0);
        } else if (360 - gyro.getAngle() < angle) { 
          mecanum.driveCartesian(0.5, 0, 0);
        }
      }
    } 
    else { //angle < 180
      while ((gyro.getAngle()) != angle) {
        if (gyro.getAngle() < angle) {
          mecanum.driveCartesian(0.5, 0, 0);
        } else if (gyro.getAngle() > angle) {
          mecanum.driveCartesian(-0.5, 0, 0);
        }
      }
    }
  }

	public void driveTeleop(){
    //y = OI.rightJoy.getRawAxis(RobotMap.rightJoyYPort);
    //x = OI.rightJoy.getRawAxis(RobotMap.rightJoyXPort);
    //z = OI.leftJoy.getRawAxis(RobotMap.leftJoyYPort);
		mecanum.driveCartesian(OI.leftJoy.getRawAxis(RobotMap.leftJoyYPort), OI.rightJoy.getRawAxis(RobotMap.rightJoyXPort), OI.rightJoy.getRawAxis(RobotMap.rightJoyYPort), gyro.getAngle());
	}

  public void driveDistance(double ticks) {
    double margin = 5;
    double originalAngle = gyro.getAngle(); 

    while (leftEncoder.getPosition() < ticks) {
      if (gyro.getAngle() - originalAngle - margin > 180 || gyro.getAngle() - originalAngle + margin > 180) {
        mecanum.driveCartesian(0.05, 0.5, 0);
      } else if (gyro.getAngle() < 180 && (gyro.getAngle() - originalAngle - margin > 0 || gyro.getAngle() - originalAngle + margin > 0)) {
        mecanum.driveCartesian(-0.05, 0.5, 0);
      } else {//above two while loops align robot straight (like done previously for climb)
        mecanum.driveCartesian(0, 0.5, 0); //when all done, move robot along the x-axis according to magnitude
      }
    }
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
