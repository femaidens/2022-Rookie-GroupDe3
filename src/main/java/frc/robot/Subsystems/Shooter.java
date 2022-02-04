// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/** Add your docs here. */
public class Shooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Solenoid leftPis = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.leftPisPort);
  public static Solenoid rightPis = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.rightPisPort);

  public void retract(){
    leftPis.set(false);
    rightPis.set(false);
  }

  public void extend(){
    leftPis.set(true);
    rightPis.set(true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
