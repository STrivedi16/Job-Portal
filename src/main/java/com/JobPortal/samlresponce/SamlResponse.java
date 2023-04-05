package com.JobPortal.samlresponce;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

@Service
public class SamlResponse {


	public  String decodeMessage(String SAMLContent) throws Exception {
  
//		String saml=
//				 "PHNhbWxwOlJlc3BvbnNlIElEPSJfNjI2YmIzMjEtODliNy00ZGFkLTkzNTQtNTM3ZjM5YjEyNjNjIiBWZXJzaW9uPSIyLjAiIElzc3VlSW5zdGFudD0iMjAyMy0wNC0wNFQwNzo0NzoyMy4xNzdaIiBEZXN0aW5hdGlvbj0iaHR0cHM6Ly9ncGxhbGNoZW15LXFhLm5pbWFwaW5mb3RlY2guY29tL3Jlc3BvbnNlIiBJblJlc3BvbnNlVG89Il9mZWE2NWUzNy0zMmI2LTQ2NjYtYjhlMy1jMDM2OGE2NDlhNzUiIHhtbG5zOnNhbWxwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiPjxJc3N1ZXIgeG1sbnM9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iPmh0dHBzOi8vc3RzLndpbmRvd3MubmV0L2JmYTNkZmIwLTkxZDUtNGJmNy05YTBjLWZiZjZmZjMzNzE4Ny88L0lzc3Vlcj48c2FtbHA6U3RhdHVzPjxzYW1scDpTdGF0dXNDb2RlIFZhbHVlPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6c3RhdHVzOlN1Y2Nlc3MiLz48L3NhbWxwOlN0YXR1cz48QXNzZXJ0aW9uIElEPSJfZDhjMmZjMjAtYjFkYy00MTBmLTgwMmUtM2NhMTFkYjE0ZDAwIiBJc3N1ZUluc3RhbnQ9IjIwMjMtMDQtMDRUMDc6NDc6MjMuMTczWiIgVmVyc2lvbj0iMi4wIiB4bWxucz0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiI+PElzc3Vlcj5odHRwczovL3N0cy53aW5kb3dzLm5ldC9iZmEzZGZiMC05MWQ1LTRiZjctOWEwYy1mYmY2ZmYzMzcxODcvPC9Jc3N1ZXI+PFNpZ25hdHVyZSB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnIyI+PFNpZ25lZEluZm8+PENhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiLz48U2lnbmF0dXJlTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8wNC94bWxkc2lnLW1vcmUjcnNhLXNoYTI1NiIvPjxSZWZlcmVuY2UgVVJJPSIjX2Q4YzJmYzIwLWIxZGMtNDEwZi04MDJlLTNjYTExZGIxNGQwMCI+PFRyYW5zZm9ybXM+PFRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNlbnZlbG9wZWQtc2lnbmF0dXJlIi8+PFRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIvPjwvVHJhbnNmb3Jtcz48RGlnZXN0TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8wNC94bWxlbmMjc2hhMjU2Ii8+PERpZ2VzdFZhbHVlPkdpUXRoWlhHbm5xV2J5cklxREJLckp0R3lZamsvamsyZTQvY2VVeDUrZTQ9PC9EaWdlc3RWYWx1ZT48L1JlZmVyZW5jZT48L1NpZ25lZEluZm8+PFNpZ25hdHVyZVZhbHVlPmtUdHp0NlRUTzJIMXo1RkxYQm1JT05oVWRxSkt2eGZYZ1FFeXc5ditCM0UrM05iVW1xdmRRRXdDeExLemtpYk5RdTVJaGdBR053VG1BMFlFdXRDWldOd09IVVZqOW1HVXRvVDVoc1pnOEt1YW93bUVFS1puSzBYM0JOdzBQQ2NnVS9MdG41N1h3SE1aQkNnalVuQkVQUGxzSmNMUzlLSitKbENUSWtDNXBVVXpDN054aTFnRHhNemNZRG5KSmwzVHdrL0NTeWd2YkxtYWxqOE5odDNNTnZqa3dNS2FrMkgrbHYrNlMySHVxS2IzS09vcUw1YzJpZDBJSXAvRUtQUXFTTWF3RWlCdXZVd1JvUVpuVFZMT0dycE9yOG5JdWFHOTM0U3ZwR1I4VDhPUk9lamV0ZndROWxKOEN1S3BaRUFIakFKNklCT1VUZk1manIvTFJsS1VOQT09PC9TaWduYXR1cmVWYWx1ZT48S2V5SW5mbz48WDUwOURhdGE+PFg1MDlDZXJ0aWZpY2F0ZT5NSUlDOERDQ0FkaWdBd0lCQWdJUWNPWXVidlowSktSRU5MaDBiQWp0ZXpBTkJna3Foa2lHOXcwQkFRc0ZBREEwTVRJd01BWURWUVFERXlsTmFXTnliM052Wm5RZ1FYcDFjbVVnUm1Wa1pYSmhkR1ZrSUZOVFR5QkRaWEowYVdacFkyRjBaVEFlRncweU16QXpNakV3TnpJMk5EUmFGdzB5TmpBek1qRXdOekkyTkRSYU1EUXhNakF3QmdOVkJBTVRLVTFwWTNKdmMyOW1kQ0JCZW5WeVpTQkdaV1JsY21GMFpXUWdVMU5QSUVObGNuUnBabWxqWVhSbE1JSUJJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBUThBTUlJQkNnS0NBUUVBb3AyNWlMR3ZNSHVWZTJGVXJXektuQ1laWXlyYnFWdTVlMEs1SzhZSXpVN3hjaG5jODFRbHlKWXB0aXlBK3FrajdHNndDc1ltK1A1b2dBMGNIRi9vTnlEVTJKQWZ1ZXkvZGFQRVRVRWlQY2dVMjUvRllhZjA4WExCOTVXMnBWMmh6ZFBIQW9UUEdobVdsL2pzdytRQzZxQ3JqMlkxbitGd3dTUFU1VW1PbXZlVnFpMzVhWlpDS2RybmUwaEJnbFRIcG1vU0lNbHFwb1o3ZzVTSEdySlhQeFBwV2trNkhJMlFmME5LMTZqVXdKZHVtRTQrVFhDSWh5NXBTWGlhWUpkZEZxRGZSSjRNcTl3WkZxOGYzS1FEUmFOQ3Y5Mnp0dkZNaTBaOXVmblpyZ29vK2pBd0FKbi9iVGlMSHU3ZkZYSisvT0FienhzUWJXbDhaQVVFUkNNczFRSURBUUFCTUEwR0NTcUdTSWIzRFFFQkN3VUFBNElCQVFDUC9BckVJbDBXTTdhL1AvUXN0YUgrMjQ4NFdaejd5WEpYN3U0cVJLL2tCeGxSS0hreWtpdHdHTk9VZURsZnh5V3p5Qi9JUVhsdk9KNEp4QWRBNEpPOEhHVUZISHFsbE83dXNaS0M5REczZGJQcUpBbVE3VzNXZkc5MFdRbE1PQUNJb2h0bTRQWUdMTkZLS21Ec3FjLzFUSzlsYUZHWXRwNDB1Tnc4TlIvMGJHc1h3ME9OS004cllyVC9xM2x6N3c5MXF4TXBxWHIvYzRxR3RGR3I4QWpEM1VJdkRtb3Z1SSs2OWlmWUxNcEY0bUQyRmZsQ3ZOMGV2ZUgxcWZ4c1J6ODgxNDNQUXM5VWJMYmx0V0xPVlh5elNGMEhkL1JXNE1uZ0RwOVR1WitDTHFucGZtYzNvUmZzS0NOL0VCV05DMXpLRjFDcGV0eDdqa0lsYThIaG4vUWY8L1g1MDlDZXJ0aWZpY2F0ZT48L1g1MDlEYXRhPjwvS2V5SW5mbz48L1NpZ25hdHVyZT48U3ViamVjdD48TmFtZUlEIEZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6MS4xOm5hbWVpZC1mb3JtYXQ6ZW1haWxBZGRyZXNzIj5yb2hpdC5zb25pQGdvZHJlanByb3BlcnRpZXMuY29tPC9OYW1lSUQ+PFN1YmplY3RDb25maXJtYXRpb24gTWV0aG9kPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6Y206YmVhcmVyIj48U3ViamVjdENvbmZpcm1hdGlvbkRhdGEgSW5SZXNwb25zZVRvPSJfZmVhNjVlMzctMzJiNi00NjY2LWI4ZTMtYzAzNjhhNjQ5YTc1IiBOb3RPbk9yQWZ0ZXI9IjIwMjMtMDQtMDRUMDg6NDc6MjMuMDAwWiIgUmVjaXBpZW50PSJodHRwczovL2dwbGFsY2hlbXktcWEubmltYXBpbmZvdGVjaC5jb20vcmVzcG9uc2UiLz48L1N1YmplY3RDb25maXJtYXRpb24+PC9TdWJqZWN0PjxDb25kaXRpb25zIE5vdEJlZm9yZT0iMjAyMy0wNC0wNFQwNzo0MjoyMy4wMDBaIiBOb3RPbk9yQWZ0ZXI9IjIwMjMtMDQtMDRUMDg6NDc6MjMuMDAwWiI+PEF1ZGllbmNlUmVzdHJpY3Rpb24+PEF1ZGllbmNlPmFsY2hlbXk8L0F1ZGllbmNlPjwvQXVkaWVuY2VSZXN0cmljdGlvbj48L0NvbmRpdGlvbnM+PEF0dHJpYnV0ZVN0YXRlbWVudD48QXR0cmlidXRlIE5hbWU9Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vaWRlbnRpdHkvY2xhaW1zL3RlbmFudGlkIj48QXR0cmlidXRlVmFsdWU+YmZhM2RmYjAtOTFkNS00YmY3LTlhMGMtZmJmNmZmMzM3MTg3PC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vaWRlbnRpdHkvY2xhaW1zL29iamVjdGlkZW50aWZpZXIiPjxBdHRyaWJ1dGVWYWx1ZT43YjI5ZjQ2Zi01NmQ4LTQ5NmQtOTFkNi1lYzUwNjE0NGE0MzI8L0F0dHJpYnV0ZVZhbHVlPjwvQXR0cmlidXRlPjxBdHRyaWJ1dGUgTmFtZT0iaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS9pZGVudGl0eS9jbGFpbXMvZGlzcGxheW5hbWUiPjxBdHRyaWJ1dGVWYWx1ZT5Sb2hpdCBTb25pPC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vaWRlbnRpdHkvY2xhaW1zL2lkZW50aXR5cHJvdmlkZXIiPjxBdHRyaWJ1dGVWYWx1ZT5odHRwczovL3N0cy53aW5kb3dzLm5ldC9iZmEzZGZiMC05MWQ1LTRiZjctOWEwYy1mYmY2ZmYzMzcxODcvPC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vY2xhaW1zL2F1dGhubWV0aG9kc3JlZmVyZW5jZXMiPjxBdHRyaWJ1dGVWYWx1ZT5odHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvYXV0aGVudGljYXRpb25tZXRob2QvcGFzc3dvcmQ8L0F0dHJpYnV0ZVZhbHVlPjxBdHRyaWJ1dGVWYWx1ZT5odHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL2NsYWltcy9tdWx0aXBsZWF1dGhuPC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL2dpdmVubmFtZSI+PEF0dHJpYnV0ZVZhbHVlPlJPSElUPC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL3N1cm5hbWUiPjxBdHRyaWJ1dGVWYWx1ZT5TT05JPC9BdHRyaWJ1dGVWYWx1ZT48L0F0dHJpYnV0ZT48QXR0cmlidXRlIE5hbWU9Imh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL2VtYWlsYWRkcmVzcyI+PEF0dHJpYnV0ZVZhbHVlPnJvaGl0LnNvbmlAZ29kcmVqcHJvcGVydGllcy5jb208L0F0dHJpYnV0ZVZhbHVlPjwvQXR0cmlidXRlPjxBdHRyaWJ1dGUgTmFtZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI+PEF0dHJpYnV0ZVZhbHVlPnJvaGl0LnNvbmlAZ29kcmVqcHJvcGVydGllcy5jb208L0F0dHJpYnV0ZVZhbHVlPjwvQXR0cmlidXRlPjwvQXR0cmlidXRlU3RhdGVtZW50PjxBdXRoblN0YXRlbWVudCBBdXRobkluc3RhbnQ9IjIwMjMtMDQtMDRUMDc6MzY6NTYuMjEyWiIgU2Vzc2lvbkluZGV4PSJfZDhjMmZjMjAtYjFkYy00MTBmLTgwMmUtM2NhMTFkYjE0ZDAwIj48QXV0aG5Db250ZXh0PjxBdXRobkNvbnRleHRDbGFzc1JlZj51cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YWM6Y2xhc3NlczpQYXNzd29yZDwvQXV0aG5Db250ZXh0Q2xhc3NSZWY+PC9BdXRobkNvbnRleHQ+PC9BdXRoblN0YXRlbWVudD48L0Fzc2VydGlvbj48L3NhbWxwOlJlc3BvbnNlPg=="
//				
				;
		String s1 = null;
		
		String status=null;
		String email=null;
		
		List<String > list=new ArrayList<>();
		 SamlUser user=new SamlUser();
		
      	try {
      		
      		System.err.println("adaskldjasldjasdklajdaskldjasdljldjaskldj");
      		System.out.println(SAMLContent);
//      		String s2=SAMLContent.trim();
      		System.err.println("S2 :"+SAMLContent);
//      		byte[] decode = Base64.getDecoder().decode(s2);
      		byte[] s=Base64.getDecoder().decode(SAMLContent);
//               System.err.println(s);
               
               String response=new String(s);
               System.err.println("adaskldjasldjasdklajdaskldjasdljldjaskldj");
               
             System.out.println("Response "+response);  
             
             
             
             
             Pattern pattern3 = Pattern.compile("<samlp:StatusCode\\s+Value=\"([^\"]*)\""); 
            
             
             Matcher matcher = pattern3.matcher(response);
             
           
             	
             
            if(matcher.find())
             {
            	s1=matcher.group(1);
            	String statusCode =s1;
            	
                String[] parts = statusCode.split(":");
                status = parts[parts.length - 1]; // get the last element of the array
                System.out.println(status);
           	  
             }
             
             
             if(status.equals("Success"))
             {
             
             
             String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,}\\b";
             Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
             Matcher matcher1 = pattern.matcher(response);
             	if(matcher1.find())
             	{
             		 email = matcher1.group();
             		System.err.println(email);
             	}
           
            
            
            
         	
            
            
            System.err.println(email);
//            System.err.println("11123123123113");
//           
//            user.setEmaill(email);
//            user.setStatus(status);
//            
//            list.add(email);
//            list.add(status);
            
            return email;
             
             }
            else
          	{
          		throw new Exception();
          	}
             
          } catch (IllegalArgumentException e) {
             e.printStackTrace();
           return null;
          }
      	
      	
      	
      	
	
	
        }
	
}

      //  return SAMLContent;

    
	
