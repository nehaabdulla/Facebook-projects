<?php
class Model_api extends CI_Model
{
	public function __construct()
	{
		parent::__construct();
		$this->load->library('session');
	}

	function register() 
	{
		if(isset($_REQUEST['fname'])&& isset($_REQUEST['lname'])&&isset($_REQUEST['address'])&&isset($_REQUEST['mobile_phone_no'])&&isset($_REQUEST['email'])&&isset($_REQUEST['password']))
		{
			$str=$this->input->get_post('fname');
			$str1=$this->input->get_post('lname');
			$str3=$this->input->get_post('mobile_phone_no');
			$str4=$this->input->get_post('email');
			$l=strlen($this->input->get_post('password'));
			$pattern = "/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/";
			if(preg_match('/^[0-9]+$/', $str))
			{
				$response['data']="Invalid input format for first name";
	 			$response['error_code']="500";
	 			echo json_encode($response);
	 			die();
			}
			else if(preg_match('/^[0-9]+$/',$str1))
			{
				$response['data']="Invalid input format for last name";
	 			$response['error_code']="500";
	 			echo json_encode($response);
	 			die();
			}
			else if(preg_match('/^[a-z]+$/',$str3))
			{
				$response['data']="Invalid input format for mobile_phone_no";
	 			$response['error_code']="500";
	 			echo json_encode($response);
	 			die();
			}
			else if(!(preg_match($pattern,$str4)))
			{
				$response['data']="Invalid input format for EMAIL";
	 			$response['error_code']="500";
	 			echo json_encode($response);
	 			die();
	 		}
			else if($l<6)
			{
				$response['data']="Invalid input format for pasword";
	 			$response['error_code']="500";
	 			echo json_encode($response);
	 			die();
			}
			else
	 		{
				$data=array
				(
					'fnm'=>$this->input->get_post('fname'),
					'lnm'=>$this->input->get_post('lname'),
					'address'=>$this->input->get_post('address'),
					'mobile'=>$this->input->get_post('mobile_phone_no'),
					'email'=>$this->input->get_post('email'),
					'password'=>$this->input->get_post('password')
				);
				if($this->db->query("call csp_registration(?,?,?,?,?,?)",$data))
				{
					$response['data']="Data successfully inserted";
	 				$response['error_code']="200";
	 				echo json_encode($response);
				}
				else
				{
					$response['data']="Error in inserting data";
	 				$response['error_code']="500";
	 				echo json_encode($response);
				}
			}
			
		}
		else
		{
			$response['data']="insufficient data";
	 		$response['error_code']="500";
			echo json_encode($response);
		}
	}


	function login_user()
	{
		if(isset($_REQUEST['email'])&&isset($_REQUEST['password']))
		{
			if(isset($_REQUEST['email']))
			{
				$str4=$this->input->get_post('email');
				$pattern = "/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/";
				if(!(preg_match($pattern,$str4)))
				{
					$response['data']="Invalid input format for EMAIL";
	 				$response['error_code']="500";
	 				echo json_encode($response);
	 				die();
	 			}
	 			else
	 			{
	 				$dat['Email_id']=$this->input->get_post('email');
	 				$this->db->select('*');
					$this->db->from('tbl_login');
					$where=$this->db->where($dat);
					$query=$this->db->get();
					if($query->result())
					{
						if(isset($_REQUEST['password']))
						{
							$data['Email_id']=$this->input->get_post('email');
							$data['Password']=$this->input->get_post('password');
							$this->db->select('*');
							$this->db->from('tbl_login');
							$where=$this->db->where($data);
							$query=$this->db->get();
							if($query->result())
							{
								$response['data']=$query->result();
	 							$response['success']="200 success";
	 							echo json_encode($response);
							}
							else
							{
								$response['data']="Incorrect password";
	 							$response['error_code']="500";
	 							echo json_encode($response);
							}
						}
					}
					else
					{
						$response['data']="Incorrect email";
	 					$response['error_code']="500";
	 					echo json_encode($response);
					}
	 			}
			}
		}
		else
		{
			$response['data']="Insufficient amount of data";
	 		$response['error_code']="500";
	 		echo json_encode($response);
		}
	}

	function all_users()
	{
		$this->db->select('Email');
		$this->db->from('tbl_registration');
		$query=$this->db->get();
		if($query->result())
		{
			$response['data']=$query->result();
			$response['success']="200";
	 		echo json_encode($response);
		}
		else
		{
			$response['data']="No data found";
	 		$response['error_code']="500";
	 		echo json_encode($response);
		}

	}

	function friend_request()
	{
		if(isset($_REQUEST['Email_id_user_one'])&&isset($_REQUEST['Email_id_user_two']))
		{
			$data['Email_id']=$this->input->get_post('Email_id_user_one');
	 		$this->db->select('pk_int_login_id');
			$this->db->from('tbl_login');
			$where=$this->db->where($data);
			$query=$this->db->get();
			$q=$query->result();
			foreach($q as $row)
			{
				$role=$row->pk_int_login_id;
				
			}
			if($query->result())
			{
				if(isset($_REQUEST['Email_id_user_two']))
				{
					$dat['Email_id']=$this->input->get_post('Email_id_user_two');
	 				$this->db->select('pk_int_login_id');
					$this->db->from('tbl_login');
					$where=$this->db->where($dat);
					$query1=$this->db->get();
					$q1=$query1->result();
					foreach ($q1 as $row) 
					{
						$role1=$row->pk_int_login_id;
					
					}
					if($query1->result())
					{
						$response['data']=$this->db->query('insert into tbl_relationship (fk_int_login_id,user_two_id,status,action_user_id) values ("'.$role.'","'.$role1.'",0,"'.$role.'")');
						$response['success']="200";
						echo json_encode($response);
					}
					else
					{
						$response['data']="Invalid data";
	 					$response['error_code']="500";
	 					echo json_encode($response);
					}
				}
				else
				{
					$response['data']="Error in inserting data";
	 				$response['error_code']="500";
	 				echo json_encode($response);
				}
			}
			else
			{
				$response['data']="Invalid data";
	 			$response['error_code']="500";
	 			echo json_encode($response);
			}
			
		}
		else
		{
			$response['data']="Insufficient amount of data";
	 		$response['error_code']="500";
	 		echo json_encode($response);
		}
	}

	function show_requests()
	{
		if(isset($_REQUEST['Email_id_user_one']))
		{
			$data['Email_id']=$this->input->get_post('Email_id_user_one');
			$this->db->select('pk_int_login_id');
			$this->db->from('tbl_login');
			$where=$this->db->where($data);
			$query=$this->db->get();
			$q=$query->result();
			foreach($q as $row)
			{
				$role=$row->pk_int_login_id;
				
			}
			if($query->result())
			{
				$this->db->select('user_two_id');
				$this->db->from('tbl_relationship');
				$this->db->where('status=0 and action_user_id='.$role.'');
				$query1 = $this->db->get();
				$q1=$query1->result();
				foreach($q1 as $row)
				{
					$role1=$row->user_two_id;
					$this->db->select('Email_id');
					$this->db->from('tbl_login');
					$this->db->where('pk_int_login_id='.$role1.'');
					$query2=$this->db->get();
					if($query2->result())
					{
						$response[]=$query2->result();
						
		 				//echo json_encode([$response]);
					}
					else
					{
						$response['data']="No data found";
		 				$response['error_code']="500";
		 				//echo json_encode($response);
					}

				}
				$response['success']="200";
				echo json_encode($response);
		 	}
			else
			{
				$response['data']="Invalid data";
	 			$response['error_code']="500";
	 			//echo json_encode($response);
			}
			//echo json_encode([$response]);
		}
		else
		{
			$response['data']="Insufficient amount of data";
	 		$response['error_code']="500";
	 		//echo json_encode($response);
		}
		//
	}

	function accept_friend()
	{
		if(isset($_REQUEST['Email_id_user_one'])&&isset($_REQUEST['Email_id_user_two']))
		{
			$data['Email_id']=$this->input->get_post('Email_id_user_one');
			$this->db->select('pk_int_login_id');
			$this->db->from('tbl_login');
			$where=$this->db->where($data);
			$query=$this->db->get();
			$q=$query->result();
			foreach($q as $row)
			{
				$role=$row->pk_int_login_id;
			}
			if($query->result())
			{
				if(isset($_REQUEST['Email_id_user_two']))
				{
					$dat['Email_id']=$this->input->get_post('Email_id_user_two');
	 				$this->db->select('pk_int_login_id');
					$this->db->from('tbl_login');
					$where=$this->db->where($dat);
					$query1=$this->db->get();
					$q1=$query1->result();
					foreach ($q1 as $row) 
					{
						$role1=$row->pk_int_login_id;
					
					}
					if($query1->result())
					{
						$this->db->query('update tbl_relationship set status=1,action_user_id='.$role1.' where user_two_id='.$role1.' and fk_int_login_id='.$role.'');
						$response['success']=" Request accepted 200";
						echo json_encode($response);
					}
					else
					{
						$response['data']="invalid data";
	 					$response['error_code']="500";
	 					echo json_encode($response);
					}
				}
				else
				{
					$response['data']="Error in accepting request";
	 				$response['error_code']="500";
	 				echo json_encode($response);
				}
			}
			else
			{
				$response['data']="invalid data";
	 			$response['error_code']="500";
	 			echo json_encode($response);
			}
		}
		else
		{
			$response['data']="Insufficient amount of data";
	 		$response['error_code']="500";
	 		echo json_encode($response);
		}
	}

	function decline_friend()
	{
		if(isset($_REQUEST['Email_id_user_one'])&&isset($_REQUEST['Email_id_user_two']))
		{
			$data['Email_id']=$this->input->get_post('Email_id_user_one');
			$this->db->select('pk_int_login_id');
			$this->db->from('tbl_login');
			$where=$this->db->where($data);
			$query=$this->db->get();
			$q=$query->result();
			foreach($q as $row)
			{
				$role=$row->pk_int_login_id;
			}
			if($query->result())
			{
				if(isset($_REQUEST['Email_id_user_two']))
				{
					$dat['Email_id']=$this->input->get_post('Email_id_user_two');
	 				$this->db->select('pk_int_login_id');
					$this->db->from('tbl_login');
					$where=$this->db->where($dat);
					$query1=$this->db->get();
					$q1=$query1->result();
					foreach ($q1 as $row) 
					{
						$role1=$row->pk_int_login_id;
					
					}
					if($query1->result())
					{
						$this->db->query('update tbl_relationship set status=2,action_user_id='.$role.' where user_two_id='.$role1.' and fk_int_login_id='.$role.'');
						$response['success']=" Request declined 200";
						echo json_encode($response);
					}
					else
					{
						$response['data']="invalid data";
	 					$response['error_code']="500";
	 					echo json_encode($response);
					}
				}
				else
				{
					$response['data']="Error in accepting request";
	 				$response['error_code']="500";
	 				echo json_encode($response);
				}
			}
			else
			{
				$response['data']="invalid data";
	 			$response['error_code']="500";
	 			echo json_encode($response);
			}
		}
		else
		{
			$response['data']="Insufficient amount of data";
	 		$response['error_code']="500";
	 		echo json_encode($response);
		}
	}


	function block_friend()
	{
		if(isset($_REQUEST['Email_id_user_one'])&&isset($_REQUEST['Email_id_user_two']))
		{
			$data['Email_id']=$this->input->get_post('Email_id_user_one');
			$this->db->select('pk_int_login_id');
			$this->db->from('tbl_login');
			$where=$this->db->where($data);
			$query=$this->db->get();
			$q=$query->result();
			foreach($q as $row)
			{
				$role=$row->pk_int_login_id;
			}
			if($query->result())
			{
				if(isset($_REQUEST['Email_id_user_two']))
				{
					$dat['Email_id']=$this->input->get_post('Email_id_user_two');
	 				$this->db->select('pk_int_login_id');
					$this->db->from('tbl_login');
					$where=$this->db->where($dat);
					$query1=$this->db->get();
					$q1=$query1->result();
					foreach ($q1 as $row) 
					{
						$role1=$row->pk_int_login_id;
					
					}
					if($query1->result())
					{
						$this->db->query('update tbl_relationship set status=3,action_user_id='.$role.' where user_two_id='.$role1.' and fk_int_login_id='.$role.'');
						$response['success']=" blocked 200";
						echo json_encode($response);
					}
					// else
					// {
					// 	$response['data']="invalid data";
	 			// 		$response['error_code']="500";
	 			// 		echo json_encode($response);
					// }
				}
				else
				{
					$response['data']="Error in accepting request";
	 				$response['error_code']="500";
	 				echo json_encode($response);
				}
			}
			else
			{
				$response['data']="invalid data";
	 			$response['error_code']="500";
	 			echo json_encode($response);
			}
		}
		else
		{
			$response['data']="Insufficient amount of data";
	 		$response['error_code']="500";
	 		echo json_encode($response);
		}
	}

	function show_friends()
	{
		if(isset($_REQUEST['Email_id_user_one']))
		{
			$data['Email_id']=$this->input->get_post('Email_id_user_one');
			$this->db->select('pk_int_login_id');
			$this->db->from('tbl_login');
			$where=$this->db->where($data);
			$query=$this->db->get();
			$q=$query->result();
			foreach($q as $row)
			{
				$role=$row->pk_int_login_id;

			}
			if($query->result())
			{
				$this->db->select('user_two_id');
				$this->db->from('tbl_relationship');
				$this->db->where('status=1 and fk_int_login_id='.$role.'');
				$query1 = $this->db->get();
				$q1=$query1->result();
				foreach($q1 as $row)
				{
					$role1=$row->user_two_id;
					$this->db->select('Email_id');
					$this->db->from('tbl_login');
					$this->db->where('pk_int_login_id='.$role1.'');
					$query2=$this->db->get();
					if($query2->result())
					{
						$response[]=$query2->result();
						
		 				
		 				//echo json_encode([$response]);
					}
					else
					{
						$response['data']="No data found";
		 				$response['error_code']="500";
		 				echo json_encode($response);
					}

				}
				$response['success']="200";
				echo json_encode($response);
				
			}
			else
			{
				$response['data']="Invalid data";
	 			$response['error_code']="500";
	 			echo json_encode($response);
			}
			//echo json_encode([$response]);
		}
		else
		{
			$response['data']="Insufficient amount of data";
	 		$response['error_code']="500";
	 		echo json_encode($response);
		}
		//

		}


	function friends_details()
	{
		if(isset($_REQUEST['Email_id_user_one'])&&isset($_REQUEST['Email_id_user_two']))
		{
			$data['Email_id']=$this->input->get_post('Email_id_user_one');
			$this->db->select('pk_int_login_id');
			$this->db->from('tbl_login');
			$where=$this->db->where($data);
			$query=$this->db->get();
			$q=$query->result();
			foreach($q as $row)
			{
				$role=$row->pk_int_login_id;
			}
			if($query->result())
			{
				if(isset($_REQUEST['Email_id_user_two']))
				{
					$dat['Email_id']=$this->input->get_post('Email_id_user_two');
	 				$this->db->select('pk_int_login_id');
					$this->db->from('tbl_login');
					$where=$this->db->where($dat);
					$query1=$this->db->get();
					$q1=$query1->result();
					foreach ($q1 as $row) 
					{
						$role1=$row->pk_int_login_id;
					
					}
					if($query1->result())
					{
						$this->db->select('first_name,last_name,address,Mobile_no,Email');
						$this->db->from('tbl_registration');
						$where=$this->db->where('fk_int_login_id='.$role1.'');
						$query2=$this->db->get();
						$response[]=$query2->result();
						$response['success']=" success 200";
						echo json_encode($response);
						
					}

					else
					{
						$response['data']="invalid data";
	 					$response['error_code']="500";
	 					echo json_encode($response);
					}
					
				}
				else
				{
					$response['data']="Error in data";
	 				$response['error_code']="500";
	 				echo json_encode($response);
				}
			}
			else
			{
				$response['data']="invalid data";
	 			$response['error_code']="500";
	 			echo json_encode($response);
			}
		}
		else
		{
			$response['data']="Insufficient amount of data";
	 		$response['error_code']="500";
	 		echo json_encode($response);
		}
	}






	}







	
	