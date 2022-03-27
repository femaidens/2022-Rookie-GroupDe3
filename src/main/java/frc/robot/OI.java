// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Commands.*;

/** Add your docs here. */
public class OI {
    public static Joystick Joy = new Joystick(RobotMap.JoyPort);
    public static Joystick leftJoy = new Joystick(RobotMap.leftJoyPort);
    public static Joystick rightJoy = new Joystick(RobotMap.rightJoyPort); 
    public static JoystickButton driveButton = new JoystickButton(Joy, RobotMap.driveButtonPort);
    public static JoystickButton gyroResetButton = new JoystickButton(Joy, RobotMap.gyroResetButtonPort);
    public void bindButton(){
        driveButton.whenReleased(new driveTeleop());
        gyroResetButton.whenPressed(new gyroReset());
    }
} //TEST!
