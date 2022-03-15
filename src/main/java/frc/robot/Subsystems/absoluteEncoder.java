// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Encoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;
import frc.robot.Commands.testEncoder;

/** Add your docs here. */
public class absoluteEncoder extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static CANSparkMax motor = new CANSparkMax(RobotMap.motorPort, MotorType.kBrushless);
  public static Encoder encoder = new Encoder(0,1);

  public void printDist(){
    System.out.println("dist: " + encoder.getDistance());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new testEncoder());
  }
}
