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
import frc.robot.OI;
import frc.robot.Commands.shootPID;

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

  public void driveC(double x, double y, double z) {
    mecanum.driveCartesian(z,x,y);
  }
  public void turnDegrees(double angle) { //ISSUE: gyro = 200 ddeg, wanted angle = 359 deg
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

	public void DriveTeleop(){
    /* rearRight.setVoltage(outputVolts);
    rearLeft.setVoltage(outputVolts);
    frontRight.setVoltage(outputVolts);
    frontLeft.setVoltage(outputVolts); */
    double y = -OI.leftJoy.getRawAxis(1);
    double x = OI.leftJoy.getRawAxis(0);
    double z = OI.rightJoy.getRawAxis(0);
		mecanum.driveCartesian(z, x, y, 0);
    //
    System.out.println("rLvolt: " + rearLeft.getBusVoltage());
    System.out.println("rRvolt: " + rearRight.getBusVoltage());
    System.out.println("fLvolt: " + frontLeft.getBusVoltage());
    System.out.println("fRvolt: " + frontRight.getBusVoltage());

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
    setDefaultCommand(new shootPID(0.1));
  }
}
