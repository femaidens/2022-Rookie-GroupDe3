// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.Commands.changeBar;
import frc.robot.Commands.climbMid;
import edu.wpi.first.wpilibj.Joystick;

/** Add your docs here. */
public class OI {
    public static Joystick leftJoy = new Joystick(RobotMap.leftJoyPort);
    public static Joystick rightJoy = new Joystick(RobotMap.rightJoyPort);
    public static Button climbMidButton = new JoystickButton(rightJoy, RobotMap.climbMidButtonPort);
    public static Button changeBarButton = new JoystickButton(rightJoy, RobotMap.changeBarButtonPort);


    public void bindButton(){    
        climbMidButton.whenPressed(new climbMid());
        changeBarButton.whenPressed(new changeBar());
    }
}
