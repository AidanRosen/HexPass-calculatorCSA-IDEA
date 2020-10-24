Progress on the calculator MVC version: 

We've designated CalculatorUI.java as the view, CalculatorControl.java as the control, and Mathstuff.java as the model. 

1. We are having trouble getting listeners into the CalculatorControl file. In other words, we are unable to get them out of the 
    CalculatorUI file and then into the CalculatorControl file without error
        
        Possible reasons:
            1. The buttons are created as their own objects and are not part of the CalculatorUI object or frame.
            2. The above is indicated by how we cannot refer to buttons by doing CalculatorUI.button_1. We end up with 
                a symbol error 
                
    However, we are able to make actual variables public and thus usable/referable in the CalculatorControl. Since those 
        variables are used entirely for control, they have just been copy-pasted over. 
        
    For seeing this error in action, please uncomment the block comment in CalculatorControl.java. to see how we tried to port over 
    the listener code and how it continued not to work despite using dot notation with calculator.button_1 (where calculator is an object created
    from the CalculatorUI class)
        
        
    
     