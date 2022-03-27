// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Commands.ClimbPID;
import frc.robot.Commands.armExtend;
import frc.robot.Commands.armRetract;
import frc.robot.Commands.pistonIn;
import frc.robot.Commands.pistonOut;


/** Add your docs here. */
public class OI {
    public static Joystick joy =  new Joystick(RobotMap.joyPort);
    public static Joystick leftJoy = new Joystick(RobotMap.leftJoyPort);
    public static Joystick rightJoy = new Joystick(RobotMap.rightJoyPort);

    public static JoystickButton climbPIDButton = new JoystickButton(leftJoy, RobotMap.climbPIDButtonPort);
    public static JoystickButton armExtendButton = new JoystickButton(joy, RobotMap.armExtendButtonPort);
    public static JoystickButton armRetractButton = new JoystickButton(joy, RobotMap.armRetractButtonPort);
    public static JoystickButton pistonInButton = new JoystickButton(joy, RobotMap.pistonInButtonPort);
    public static JoystickButton pistonOutButton = new JoystickButton(joy, RobotMap.pistonOutButtonPort);



    public void bindButton(){    
        armExtendButton.whenPressed(new armExtend());
        armRetractButton.whenPressed(new armRetract());
        pistonInButton.whenPressed(new pistonIn());
        pistonOutButton.whenPressed(new pistonOut());
        climbPIDButton.whenPressed(new ClimbPID(0, 0.1));
    }
}
