/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.fireteam322.frc.robot.commands;

import com.fireteam322.frc.robot.Constants;
import com.fireteam322.frc.robot.subsystems.RobotCamera;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunFrontCamera extends CommandBase {
  private final RobotCamera m_frontCamera;

  /** Creates a new RunFrontCamera. */
  public RunFrontCamera(RobotCamera frontCamera) {
    m_frontCamera = frontCamera;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_frontCamera);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_frontCamera.setResolution(Constants.VIDEO_WIDTH, Constants.VIDEO_HEIGHT);
    m_frontCamera.setFPS(Constants.VIDEO_FPS);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public boolean runsWhenDisabled() {
    return true;
  }
}
