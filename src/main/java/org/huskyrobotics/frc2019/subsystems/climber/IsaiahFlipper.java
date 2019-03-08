package org.huskyrobotics.frc2019.subsystems.climber;

import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IsaiahFlipper extends Subsystem {
    public void initDefaultCommand() 
    {
      //setDefaultCommand(new UseDrivetrain());
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
    private TalonSRX m_winchMotor;
    private Solenoid m_sol;
    private Solenoid m_lockLeft;
    private Solenoid m_lockRight;

    private boolean m_solActive;

    private boolean m_controlActive = false;

    public IsaiahFlipper (int winchMotorPort, int solenoidChannel, int leftLockChannel, int rightLockChannel) {
        m_winchMotor = new TalonSRX(winchMotorPort);
        m_sol = new Solenoid(solenoidChannel);
	m_lockLeft = new Solenoid(leftLockChannel);
	m_rightLeft = new Solenoid(rightLockChannel);
    }
    //releases the winch rope
    public void setWinchAxis(double input) {
        if(m_controlActive) {
            if(Math.abs(input) > 0.1) {
                m_winchMotor.set(ControlMode.PercentOutput, input);
            } else {
                m_winchMotor.set(ControlMode.PercentOutput, 0);
            }
        }
    }
	public void setActive (boolean input) {
		m_controlActive = input;
	}
    public void periodic() {
        if(m_controlActive) {
            clamp(true);
			unlock();
        }
        m_sol.set(m_solActive);
    }

    //Clamps onto the platform so winch can pull robot up.
    //True for clamped, false for released/
    public void clamp(boolean clamp) {
        m_solActive = clamp;
    }
    public void toggleClamp() {
        m_solActive = !m_solActive;
    }
	public void lock() {
		m_lockLeft.set(false);
		m_lockRight.set(false);
	}
	public void unlock() {
		m_lockLeft.set(true);
		m_lockRight.set(true);
	}

    public boolean getClamped() {
        return(m_solActive);
    }
    //stops the winch
    public void stopWinch() {
        m_winchMotor.set(ControlMode.PercentOutput, 0.0);
    }
}

