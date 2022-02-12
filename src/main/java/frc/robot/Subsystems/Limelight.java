// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/** Add your docs here. */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry tv = table.getEntry("tv");
  public static NetworkTableEntry tx = table.getEntry("tx");
  public static NetworkTableEntry ta = table.getEntry("ta");
  public static NetworkTableEntry ts = table.getEntry("ts");
  public static NetworkTableEntry ty = table.getEntry("ty");

  public void printValues(){
    //System.out.println("tv: " + tv.getDouble(0));
    //System.out.println("tx: " + tx.getDouble(0));
    //System.out.println("ta: " + ta.getDouble(0));
    //System.out.println("ts: " + ts.getDouble(0));
    //System.out.println("ty: " + ty.getDouble(0));
    System.out.println(tx.getDouble(0) + ", " + ty.getDouble(0));
  }

  public double getTx() {
    return tx.getDouble(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
} //hi
