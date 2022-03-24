// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Commands.ClimbPID;
import frc.robot.Commands.changeBar;
import edu.wpi.first.wpilibj.Joystick;

/** Add your docs here. */
public class OI {
    public static Joystick leftJoy = new Joystick(RobotMap.leftJoyPort);
    public static Joystick rightJoy = new Joystick(RobotMap.rightJoyPort);
    public static Button climbMidButton = new JoystickButton(rightJoy, RobotMap.climbMidButtonPort);
    public static Button changeBarButton = new JoystickButton(rightJoy, RobotMap.changeBarButtonPort);
    public static Button climbPIDButton = new JoystickButton(rightJoy, RobotMap.climbPIDButtonPort);


    public void bindButton(){    
        changeBarButton.whenPressed(new changeBar());
        climbPIDButton.whenPressed(new ClimbPID(0, 0.1));
    }
}
