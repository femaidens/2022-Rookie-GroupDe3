// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Commands.Intake;

/** Add your docs here. */
public class OI {
    public static Joystick rightJoy = new Joystick(RobotMap.rightJoyPort);
    public static JoystickButton intakeButton = new JoystickButton(rightJoy, RobotMap.intakeButtonPort);
    public void bindButton(){
        intakeButton.whileHeld(new Intake());
    }
}
