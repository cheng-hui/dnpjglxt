<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	var search_type_storage = "none";
	var search_keyWord = "";
	var search_repository = "";
	var select_goodsID;
	var select_repositoryID;

	$(function() {
		optionAction();
		searchAction();
		storageListInit();
		bootstrapValidatorInit();
		repositoryOptionInit();

		addStorageAction();
		editStorageAction();
		deleteStorageAction();
	})

	// 查询方式下拉框，为search_type_storage赋值，若为所有，搜索框不能编辑
	function optionAction() {
		$(".dropOption").click(function() {
			var type = $(this).text();
			$("#search_input").val("");
			if (type == "所有") {
				$("#search_input_type").attr("readOnly", "true");
				search_type_storage = "searchAll";
			} else if (type == "配件ID") {
				$("#search_input_type").removeAttr("readOnly");
				search_type_storage = "searchByPartsID";
			} else if (type == "配件名称") {
				$("#search_input_type").removeAttr("readOnly");
				search_type_storage = "searchByPartsName";
			} else if(type = "配件类型"){
				$("#search_input_type").removeAttr("readOnly");
				search_type_storage = "searchByPartsType";
			}else {
				$("#search_input_type").removeAttr("readOnly");
			}

			$("#search_type").text(type);
			$("#search_input_type").attr("placeholder", type);
		})
	}

	// 仓库下拉框数据初始化，页面加载时完成
	function repositoryOptionInit(){
		$.ajax({
			type : 'GET',
			url : 'repositoryManage/getRepositoryList',
			dataType : 'json',
			contentType : 'application/json',
			data:{
				searchType : "searchAll",
				keyWord : "",
				offset : -1,
				limit : -1
			},
			success : function(response){
			    //组装option
				$.each(response.rows,function(index,elem){
					$('#search_input_repository').append("<option value='" + elem.repoId + "'>" + elem.repoId +"号仓库</option>");
				})
			},
			error : function(response){
			}
		});
	}

	// 搜索动作
	function searchAction() {
		$('#search_button').click(function() {
			search_keyWord = $('#search_input_type').val();
			search_repository = $('#search_input_repository').val();
            alert("searchType: " + search_type_storage + "\nrepoId: " + search_repository + "\nkeyWord: " + search_keyWord);
			tableRefresh();
		})
	}

	// 表格初始化
	function storageListInit() {
		$('#storageList')
				.bootstrapTable(
						{
							columns : [
									{
										field : 'partsId',
										title : '配件ID'
									},
									{
										field : 'partsName',
										title : '配件名称'
									},
									{
										field : 'partsType',
										title : '配件类型'
									},
									{
										field : 'repoId',
										title : '仓库ID'
									},
									{
										field : 'number',
										title : '库存数量'
									},
									{
										field : 'operation',
										title : '操作',
										formatter : function(value, row, index) {
											var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
											var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
											var fun = '';
											return s + ' ' + d;
										},
										events : {
											// 操作列中编辑按钮的动作，rowEditOperation(row)，传入row
											'click .edit' : function(e, value,
													row, index) {
												//selectID = row.id;
												rowEditOperation(row);
											},
											'click .delete' : function(e,
													value, row, index) {
												select_goodsID = row.partsId;
												select_repositoryID = row.repoId;
												$('#deleteWarning_modal').modal(
														'show');
											}
										}
									} ],
							url : 'storageManage/getStorageListWithRepository',
							method : 'GET',
							queryParams : function (params) {
								return {	// 分页查询参数
                                    limit : params.limit,
                                    offset : params.offset,
                                    searchType : search_type_storage,
                                    repoId : search_repository,
                                    keyWord : search_keyWord
								}
                            },
                            sidePagination : "server",	// 服务端分页
                            queryParamsType : "limit",
                            dataType : 'json',
                            pagination : true,
                            pageNumber : 1,
                            pageSize : 5,
                            pageList : [ 5, 10, 25, 50, 100 ],  // 可选的每页数据
                            paginationPreText: ' 上一页',
                            paginationNextText: '下一页'
						});
	}

	// 表格刷新
	function tableRefresh() {
		$('#storageList').bootstrapTable('refresh', {
			query : {}
		});
	}

	// 行编辑操作模态框展示与数据填充
	function rowEditOperation(row) {
		$('#edit_modal').modal("show");

		// load info
		$('#storage_form_edit').bootstrapValidator("resetForm", true);
		$('#storage_goodsID_edit').text(row.partsId);
		$('#storage_repositoryID_edit').text(row.repoId);
		$('#storage_number_edit').val(row.number);
	}

	// 添加库存信息模态框数据校验
	function bootstrapValidatorInit() {
		$("#storage_form").bootstrapValidator({
			message : 'This is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			excluded : [ ':disabled' ],
			fields : {
				storage_goodsID : {
					validators : {
						notEmpty : {
							message : '配件ID不能为空'
						}
					}
				},
				storage_repositoryID : {
					validators : {
						notEmpty : {
							message : '仓库ID不能为空'
						}
					}
				},
				storage_number : {
					validators : {
						notEmpty : {
							message : '库存数量不能为空'
						}
					}
				}
			}
		})
	}

	// 编辑库存信息，表单数据提交
	function editStorageAction() {
		$('#edit_modal_submit').click(
				function() {
					$('#storage_form_edit').data('bootstrapValidator')
							.validate();
					if (!$('#storage_form_edit').data('bootstrapValidator')
							.isValid()) {
						return;
					}

					// ajax
					$.ajax({
						type : "POST",
						url : 'storageManage/updateStorageRecord',
						dataType : "json",
						content : "application/json",
						data : {
                            partsId : $('#storage_goodsID_edit').text(),
                            repoId : $('#storage_repositoryID_edit').text(),
                            number : $('#storage_number_edit').val()
                        },
						success : function(response) {
							$('#edit_modal').modal("hide");
							var type;
							var msg;
							if (response.result == "success") {
								type = "success";
								msg = "库存信息更新成功";
							} else {
								type = "error";
								msg = "库存信息更新失败"
							}
							infoModal(type, msg);
							tableRefresh();
						},
						error : function(response) {
						}
					});
				});
	}

	// 刪除库存信息
	function deleteStorageAction(){
		$('#delete_confirm').click(function(){
			// ajax
			$.ajax({
				type : "POST",
				url : "storageManage/deleteStorageRecord",
				dataType : "json",
				content : "application/json",
				data : {
                    partsId : select_goodsID,
                    repoId : select_repositoryID
                },
				success : function(response){
					$('#deleteWarning_modal').modal("hide");
					var type;
					var msg;
					if(response.result == "success"){
						type = "success";
						msg = "库存信息删除成功";
					}else{
						type = "error";
						msg = "库存信息删除失败";
					}
					infoModal(type, msg);
					tableRefresh();
				},error : function(response){
				}
			})
			
			$('#deleteWarning_modal').modal('hide');
		})
	}

	// 添加库存信息
	function addStorageAction() {
		$('#add_storage').click(function() {
			$('#add_modal').modal("show");
		});

		$('#add_modal_submit').click(function() {
			// ajax
			$.ajax({
				type : "POST",
				url : "storageManage/insertStorageRecord",
				dataType : "json",
				content : "application/json",
				data : {
                    partsId : $('#storage_goodsID').val(),
                    repoId : $('#storage_repositoryID').val(),
                    number : $('#storage_number').val()
                },
				success : function(response) {
					$('#add_modal').modal("hide");
					var msg;
					var type;
					if (response.result == "success") {
						type = "success";
						msg = "库存信息添加成功";
					} else if (response.result == "error") {
						type = "error";
						msg = "库存信息添加失败";
					}
					infoModal(type, msg);
					tableRefresh();

					// reset
					$('#storage_goodsID').val("");
					$('#storage_repositoryID').val("");
					$('#storage_number').val("");
					$('#storage_form').bootstrapValidator("resetForm", true);
				},
				error : function(response) {
				}
			})
		})
	}

	// 操作结果提示模态框
	function infoModal(type, msg) {
		$('#info_success').removeClass("hide");
		$('#info_error').removeClass("hide");
		if (type == "success") {
			$('#info_error').addClass("hide");
		} else if (type == "error") {
			$('#info_success').addClass("hide");
		}
		$('#info_content').text(msg);
		$('#info_modal').modal("show");
	}
</script>
<div class="panel panel-default">
	<ol class="breadcrumb">
		<li>库存信息管理</li>
	</ol>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-1  col-sm-2">
				<div class="btn-group">
					<button class="btn btn-default dropdown-toggle"
						data-toggle="dropdown">
						<span id="search_type">查询方式</span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="javascript:void(0)" class="dropOption">配件ID</a></li>
						<li><a href="javascript:void(0)" class="dropOption">配件名称</a></li>
						<li><a href="javascript:void(0)" class="dropOption">配件类型</a></li>
						<li><a href="javascript:void(0)" class="dropOption">所有</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-9 col-sm-9">
				<div>
					<div class="col-md-3 col-sm-3">
						<input id="search_input_type" type="text" class="form-control"
							placeholder="配件ID">
					</div>
					<!--通过后台查询仓库信息-->
					<div class="col-md-3 col-sm-4">
						<select class="form-control" id="search_input_repository">
						</select>
					</div>
					<div class="col-md-2 col-sm-2">
						<button id="search_button" class="btn btn-success">
							<span class="glyphicon glyphicon-search"></span> <span>查询</span>
						</button>
					</div>
				</div>
			</div>
		</div>

		<div class="row" style="margin-top: 25px">
			<div class="col-md-5">
				<button class="btn btn-sm btn-default" id="add_storage">
					<span class="glyphicon glyphicon-plus"></span> <span>添加库存信息</span>
				</button>
			</div>
			<div class="col-md-5"></div>
		</div>

		<div class="row" style="margin-top: 15px">
			<div class="col-md-12">
				<table id="storageList" class="table table-striped"></table>
			</div>
		</div>
	</div>
</div>

<!-- 添加库存信息模态框 -->
<div id="add_modal" class="modal fade" table-index="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">添加库存记录</h4>
			</div>
			<div class="modal-body">
				<!-- 添加库存信息模态框的内容 -->
				<div class="row">
					<div class="col-md-1 col-sm-1"></div>
					<div class="col-md-8 col-sm-8">
						<form class="form-horizontal" role="form" id="storage_form"
							style="margin-top: 25px">
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>配件ID：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="storage_goodsID"
										name="storage_goodsID" placeholder="配件ID">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库ID：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="storage_repositoryID"
										name="storage_repositoryID" placeholder="仓库ID">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>数量：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="storage_number"
										name="storage_number" placeholder="数量">
								</div>
							</div>
						</form>
					</div>
					<div class="col-md-1 col-sm-1"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>取消</span>
				</button>
				<button class="btn btn-success" type="button" id="add_modal_submit">
					<span>提交</span>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 提示消息模态框 -->
<div class="modal fade" id="info_modal" table-index="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">信息</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-4 col-sm-4"></div>
					<div class="col-md-4 col-sm-4">
						<div id="info_success" class=" hide" style="text-align: center;">
							<img src="media/icons/success-icon.png" alt=""
								style="width: 100px; height: 100px;">
						</div>
						<div id="info_error" style="text-align: center;">
							<img src="media/icons/error-icon.png" alt=""
								style="width: 100px; height: 100px;">
						</div>
					</div>
					<div class="col-md-4 col-sm-4"></div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-4 col-sm-4"></div>
					<div class="col-md-4 col-sm-4" style="text-align: center;">
						<h4 id="info_content"></h4>
					</div>
					<div class="col-md-4 col-sm-4"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;</span>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 删除提示模态框 -->
<div class="modal fade" id="deleteWarning_modal" table-index="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">警告</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-3 col-sm-3" style="text-align: center;">
						<img src="media/icons/warning-icon.png" alt=""
							style="width: 70px; height: 70px; margin-top: 20px;">
					</div>
					<div class="col-md-8 col-sm-8">
						<h3>是否确认删除该条库存信息</h3>
						<p>(注意：一旦删除该条库存信息，将不能恢复)</p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>取消</span>
				</button>
				<button class="btn btn-danger" type="button" id="delete_confirm">
					<span>确认删除</span>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 编辑库存模态框 -->
<div id="edit_modal" class="modal fade" table-index="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">编辑配件信息</h4>
			</div>
			<div class="modal-body">
				<!-- 模态框的内容 -->
				<div class="row">
					<div class="col-md-1 col-sm-1"></div>
					<div class="col-md-8 col-sm-8">
						<form class="form-horizontal" role="form" id="storage_form_edit"
							style="margin-top: 25px">
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>配件ID：</span>
								</label>
								<div class="col-md-4 col-sm-4">
									<p id="storage_goodsID_edit" class="form-control-static"></p>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库ID：</span>
								</label>
								<div class="col-md-4 col-sm-4">
									<p id="storage_repositoryID_edit" class="form-control-static"></p>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>数量：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="storage_number_edit"
										name="storage_number" placeholder="库存数量">
								</div>
							</div>
						</form>
					</div>
					<div class="col-md-1 col-sm-1"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>取消</span>
				</button>
				<button class="btn btn-success" type="button" id="edit_modal_submit">
					<span>确认更改</span>
				</button>
			</div>
		</div>
	</div>
</div>