// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
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
  
  public double getDistance(){
    //this = from a formula on limelight docs
    //limelightHeightInches = 27.5 in
    //goaldistfromwall = 41 in
    double limelightMountAngleDegrees = 60.0;
    double goalHeightInches = 41.0;
    double angleToGoalDegrees = limelightMountAngleDegrees + getTy();
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
    return (goalHeightInches - 27.5)/Math.tan(angleToGoalRadians); 
  }
  public double getTy() {
    return ty.getDouble(0);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
