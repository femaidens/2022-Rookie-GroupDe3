// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Commands.*;

/** Add your docs here. */
public class UltrasonicTest extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ultrasonic1Port, RobotMap.ultrasonic2Port);
  public void getDistance(){
    Ultrasonic.setAutomaticMode(true);
    System.out.println("Dist (in):" + ultrasonic.getRangeInches());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new printVal());
  }
}
