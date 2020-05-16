/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapps;

/**
 *
 * @author ak
 */
public class JScripts 
{
  public JScripts()
  {
  
  
  }
  public static String code1 = "$('#NonDeliveryMeasure').removeAttr('disabled');var values = ['B','F'];var options = ['B - Scheduled for further action/processing on next working day','F - Item forwarded/redirected'];for(var i=0;i<2;i++){$('<option/>',{value:values[i],html:options[i]}).appendTo('#NonDeliveryMeasure')}";
}
