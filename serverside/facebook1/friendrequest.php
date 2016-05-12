<?php
$conn = mysqli_connect('13.76.213.131','root','baabteadmin123!','db_facebookaslam');
if(isset($_REQUEST['Email_id_user_one']) && isset($_REQUEST['Email_id_user_two']))
{
	$Email_id_user_one = $_REQUEST['Email_id_user_one'];
	$Email_id_user_two = $_REQUEST['Email_id_user_two'];
	$query = "insert into fb_relationship (user_one_id,user_two_id,status,action_user_id) values ('$Email_id_user_one','$Email_id_user_two',0,'$Email_id_user_one')";
	$result = mysqli_query($conn,$query);
	if($result))
	{
		    $Success = array('ResponseCode' =>'200','Msg'=>'Success');
			$Successdata=json_encode($Success);
			echo $Successdata;
	}
	else
	{
		    $fail = array('ResponseCode' =>'500','Msg'=>'cannot send request' );
			$faildata=json_encode($fail);
			echo $faildata;
	}
}
else{
	    $data = array('ResponseCode' =>'500','Msg'=>'No values recieved');
		$js=json_encode($data);
		echo $js;
}
?>