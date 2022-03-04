// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMax;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Subsystems.DriveTrain;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.OI;

/** Add your docs here. */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static CANSparkMax frontLeft = new CANSparkMax(RobotMap.frontLeftPort, MotorType.kBrushless);
  public static CANSparkMax frontRight = new CANSparkMax(RobotMap.frontRightPort, MotorType.kBrushless);
	public static CANSparkMax rearLeft = new CANSparkMax(RobotMap.rearLeftPort, MotorType.kBrushless);
	public static CANSparkMax rearRight = new CANSparkMax(RobotMap.rearRightPort, MotorType.kBrushless);
	public MecanumDrive mecanum = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
  public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);

  public void drive(double x, double y, double z){
    //In other branches, may be named misleading name driveStraight; actually supposed to be drive.
    mecanum.driveCartesian(z, x, y, gyro.getAngle());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
