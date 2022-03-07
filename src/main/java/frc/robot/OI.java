// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Commands.shootPID;

/** Add your docs here. */
public class OI {
    // public static Joystick leftJoy = new Joystick(RobotMap.leftJoyPort);
    //public static Joystick rightJoy = new Joystick(RobotMap.rightJoyPort);
    public static Joystick joy = new Joystick(RobotMap.joyPort);

    public static Button PIDbutton = new JoystickButton(joy, RobotMap.buttonPort);
    public void bindButton(){
        PIDbutton.whileHeld(new shootPID(0.01));
    }
}
