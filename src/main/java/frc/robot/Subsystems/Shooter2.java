// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import frc.robot.RobotMap;
import frc.robot.Robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.command.Subsystem;

/** Add your docs here. */
public class Shooter2 extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static CANSparkMax motor = new CANSparkMax(RobotMap.Shooter2MotorPort, MotorType.kBrushless);
  public static DoubleSolenoid shooterPis = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.shooterPisForwardPort, RobotMap.shooterPisBackwardPort);
  public static DutyCycleEncoder encoder = new DutyCycleEncoder(RobotMap.shooter2EncoderPort);
  public void shoot(){
    shooterPis.set(DoubleSolenoid.Value.kForward);
  }
  public void spinMotor(){
    int ticks = 25;
    if (encoder.getPositionOffset() < ticks){
      motor.set(0.5);
    }
    else{
      motor.set(0.0);
    }
  }
  public void reset(){
    shooterPis.set(DoubleSolenoid.Value.kReverse);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
