<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Services_api extends CI_Controller {

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see http://codeigniter.com/user_guide/general/urls.html
	 */
	public function registration()
	{
		$this->load->model('Model_api');
		$this->Model_api->register($_POST);
	}

	public function login()
	{
		$this->load->model('Model_api');
		$this->Model_api->login_user($_POST);
	}

	public function users()
	{
		$this->load->model('Model_api');
		$this->Model_api->all_users($_POST);
	}

	public function friendRequest()
	{
		$this->load->model('Model_api');
		$this->Model_api->friend_request($_POST);
	}

	public function showRequests()
	{
		$this->load->model('Model_api');
		$this->Model_api->show_requests($_POST);
	}

	public function friendAccept()
	{
		$this->load->model('Model_api');
		$this->Model_api->accept_friend($_POST);
	}

	public function declineFriend()
	{
		$this->load->model('Model_api');
		$this->Model_api->decline_friend($_POST);
	}

	public function blockFriend()
	{
		$this->load->model('Model_api');
		$this->Model_api->block_friend($_POST);
	}

	public function showFriends()
	{
		$this->load->model('Model_api');
		$this->Model_api->show_friends($_POST);
	}

	public function Friends_details()
	{
		$this->load->model('Model_api');
		$this->Model_api->friends_details($_POST);
	}


}
