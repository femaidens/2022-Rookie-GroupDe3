// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import frc.robot.RobotMap;

/** Add your docs here. */
public class IntakeBall extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static CANSparkMax motor1 = new CANSparkMax(RobotMap.intakeMotorPort, MotorType.kBrushless);
  public static DoubleSolenoid leftIntakePis = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.leftIntakePisForwardPort, RobotMap.leftIntakePisBackwardPort);
  public static DoubleSolenoid rightIntakePis = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.rightIntakePisForwardPort, RobotMap.rightIntakePisBackwardPort);

  public void retract(){
    leftIntakePis.set(DoubleSolenoid.Value.kReverse);
    rightIntakePis.set(DoubleSolenoid.Value.kReverse);
  }

  public void extend(){
    leftIntakePis.set(DoubleSolenoid.Value.kForward);
    rightIntakePis.set(DoubleSolenoid.Value.kForward);
  }

  public void spinBar(){
    motor1.set(0.25);
  }

  public void stopBar(){
    motor1.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
