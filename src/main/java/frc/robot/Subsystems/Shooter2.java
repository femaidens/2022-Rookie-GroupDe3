// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import frc.robot.RobotMap;
import frc.robot.Robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.OI;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.command.Subsystem;

/** Add your docs here. */
public class Shooter2 extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static CANSparkMax motor = new CANSparkMax(RobotMap.Shooter2MotorPort, MotorType.kBrushless);
  public static DoubleSolenoid latchPis = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.latchPisForwardPort, RobotMap.latchPisBackwardPort);
  //public static DutyCycleEncoder encoder = new DutyCycleEncoder(RobotMap.shooter2EncoderPort);, reset encoder at the start
  public static RelativeEncoder encoder = motor.getEncoder();
  public static DoubleSolenoid gearPis = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.gearPisForwardPort, RobotMap.gearPisBackwardPort);
  public static boolean canShoot;
  public void shoot(){
    if (OI.joy.getRawAxis(3) > 0.2){
      latchPis.set(DoubleSolenoid.Value.kForward);
    }
  }
  public void alignShooter(){
    int ticks = 25; //until the bar/base gets latched in; encoder counts how much pulley moves
    if (encoder.getPosition() < ticks){
      motor.set(0.5);
    }
    else{ //locks the bar (the base of the shooter)
      motor.stopMotor();
    }
  }

  public void extendLatchPis(){
    latchPis.set(DoubleSolenoid.Value.kForward);
  }

  public void retractLatchPis(){
    latchPis.set(DoubleSolenoid.Value.kReverse);
  }

  public void extendGearPis(){
    latchPis.set(DoubleSolenoid.Value.kForward);
  }

  public void retractGearPis(){
    latchPis.set(DoubleSolenoid.Value.kReverse);
  }
  public boolean readyShoot(){
    return canShoot;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
