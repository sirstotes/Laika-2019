package org.huskyrobotics.frc2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.huskyrobotics.frc2019.Robot;
import org.huskyrobotics.frc2019.OI;

public class UseFlipper extends Command {
  public UseFlipper(OI oi) {
    requires(Robot.m_Armstrong);
    m_OI = oi;
  }
  OI m_OI;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_Armstrong.setActive(true);
    Robot.m_Arm.setActive(false);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_Armstrong.setWinchAxis(m_OI.getWinchAxis());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_Armstrong.setActive(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_Arm.stop();
  }
}
