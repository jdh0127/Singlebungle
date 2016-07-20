<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

 
 <tiles:insertAttribute name="hub"/>
 <tiles:insertAttribute name="body"/>
</div>
				<div class="borad_footer">
				</div>
				

<script type="text/javascript">
				
				window.onload=function(){
				      
				      $("table img").each(function(){
				         if($(this).width()>640){
				            $(this).css({"width" : "630px"});
				         }
				      });
				   }
				</script>
							
				
				</div>
			</div>
		</div><!--  hub_conent END -->
		<div id="hub_footer">
			<div></div>
		</div>
	</div>
</body>
</html>