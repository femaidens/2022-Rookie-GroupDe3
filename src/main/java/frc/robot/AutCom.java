// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.Commands.driveBack;
import frc.robot.Commands.autoAlign;
import frc.robot.Commands.shoot;
import frc.robot.Commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutCom extends CommandGroup {
  /** Add your docs here. */
  public AutCom() {
    addSequential(new driveBack()); //drive backwards
    addSequential(new autoAlign()); //align 
    addSequential(new shoot()); //shoot
    addSequential(new Intake());

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
