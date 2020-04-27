$(document).ready(function(){
    //0：问答模式 1：互动模式
    var mode = 0;
    var id = null;
    var modeMessage = "";

    var treeList = [];
    var cateList = [];
    var instance = OverlayScrollbars(document.getElementById('message'), {
        scrollbars: {
            autoHide: "l", clickScrolling: !0
        },
        className: "os-theme-light",
    });

    var socket;
    if(!window.WebSocket){
        window.WebSocket = window.MozWebSocket;
    }
    if(window.WebSocket){
        socket = new WebSocket("ws://127.0.0.1:12345/ws?uid=666&gid=777");
        socket.onmessage = function(event){
            appendLeftMessage(event.data)
        };
        socket.onopen = function(event){
            var json = {};
            json.type = "NONE";
            json.data = "已连接服务器";
            appendMiddleMessage(JSON.stringify(json));

            var json0 = {};
            json0.type = "TEXT";
            json0.data = "您好，我是小科。<br>您可以向我提问呦!";
            appendLeftMessage(JSON.stringify(json0))
        };
        socket.onclose = function(event){
            var json = {};
            json.type = "NONE";
            json.data = "与服务器断开连接";
            appendMiddleMessage(JSON.stringify(json))
        };
    }else{
        alert("您的浏览器不支持WebSocket协议！");
    }

    $("#send").click(function () {
        var $this = $(this);
        var text = $("#text");
        var textVal = text.val();
        textVal = textVal.replace(/\ +/g,"");//去掉空格
        textVal = textVal.replace(/[\r\n]/g,"");//去掉回车换行
        if(textVal){
            if(textVal=="进入学习模式"){
                mode = 2;
                id = null;
            }else if(textVal=="退出学习模式"){
                mode = 0;
                id = null;
            }
            sendText(textVal);
        }else{
            //发送内容不能为空,请重新输入
            $this.tooltip('show');
            setTimeout(function () {
                $this.tooltip('hide');
            },2000);
        }
        text.val("");
    });

    //回车发送
    $("#text").keyup(function(event){
        if(event.altKey && (event.which == 13 || event.which == 10)) {
            $("#text").val($("#text").val()+"\n");
        }else if (event.keyCode == 13) {
            event.preventDefault();
            $('#send').triggerHandler('click');
        }
    });
    
    function handleMessage(json) {
        modeMessage = "";
        mode = 0;

        var str = "";
        if(json.type == 'TEXT'){
            str = json.data;
        }else if(json.type == 'LINK'){
            var data = json.data;
            if(data.length>0){
                str += '小科猜您可能想了解以下内容：<br>';
                for (var i = 0 ;i < data.length;i++){
                    str += '<a class="questionLink" data="'+data[i]+'" href="#">'+data[i]+'</a><br>';
                }
            }else{

            }
        }else if(json.type == 'NONE'){
            str = json.data;
        }else if(json.type == 'WEATHER'){
            if(json.mode == 0){
                str = json.data;
                $("#button-"+json.message).click();
                if(json.message == 'wind'){
                    $(".weather #cloud1").css("fill","#efefef33");
                    $(".thunder .weather #cloud1").css("fill","#9FA4AD33");
                    $(".weather #cloud2").css("fill","#E6E6E633");
                    $(".thunder .weather #cloud2").css("fill","#8B8E9833");
                    $(".weather #cloud3").css("fill","#D5D5D533");
                    $(".thunder .weather #cloud3").css("fill","#7B798833");
                }else{
                    $(".weather #cloud1").css("fill","#efefef03");
                    $(".thunder .weather #cloud1").css("fill","#9FA4AD03");
                    $(".weather #cloud2").css("fill","#E6E6E603");
                    $(".thunder .weather #cloud2").css("fill","#8B8E9803");
                    $(".weather #cloud3").css("fill","#D5D5D503");
                    $(".thunder .weather #cloud3").css("fill","#7B798803");
                }
            }else{
                str = "<p>"+json.message+"</p>";
                modeMessage = json.data;
                mode = json.mode;
            }
        }else if(json.type == 'IMAGE'){
            //str = json.data;
            str = ' <div class="row lightbox photos">'+
                '<a href="'+json.data+'" class="col-md-12 col-12">'+
                '<div class="lightbox__item photos__item" style="width: 500px">'+
                '<img src="'+json.data+'" alt="" />'+
                '</div>'+
                '</a>'+
                '</div>';
        }else if(json.type == 'DOWNLOAD'){
            str = '<i class="zwicon-download" style="font-size: 18px;"></i>';
            str+= '<a href="'+json.data+'">&nbsp;&nbsp;&nbsp;'+json.message+"</a>";
        }else{
            str = json.data;
        }
        mode = json.mode;
        id = json.id;
        return str;
    }
    
    function getMessage(type,avatar,date,message) {
        var json = JSON.parse(message);
        message = handleMessage(json);
        date = getFormatDate();

        var typeClass = type === 'right'? 'messages__item--right':type === 'middle'?'messages__item--middle':'';
        var msg = '<div class="messages__item '+typeClass+'">';
        if(avatar){
            msg += '<img src="'+avatar+'" class="avatar-img" alt="">';
        }
        if(type==='middle'){
            msg += '<div class="messages__details">'+
                '<small><i class="zwicon-clock"></i> '+date + " " +message+'</small>'+
                '</div>';
        }else{
            msg += '<div class="messages__details">'+
                '<small><i class="zwicon-clock"></i> '+date+'</small>';
            if(json.type == "IMAGE" || json.type=='WEATHER'){
                msg += message;
            }else{
                msg += '<p>'+message+'</p>';
            }
            msg += '</div>';
        }
        msg += '</div>';

        var jsonMsg = {};
        jsonMsg.type = json.type;
        jsonMsg.data = msg;
        return jsonMsg;
    }

    function appendLeftMessage(message) {
        var jsonMsg = getMessage("left","/static/vendors/img/bg/1.jpg","2020-04-11 11:09:00",message);
        appendMessage(jsonMsg);
    }

    function appendMiddleMessage(message) {
        var jsonMsg = getMessage("middle","","2020-04-11 11:09:00",message);
        appendMessage(jsonMsg);
    }

    function appendRightMessage(message) {
        var jsonMsg = getMessage("right","/static/vendors/img/bg/2.jpg","2020-04-11 11:09:00",message)
        appendMessage(jsonMsg);
    }

    //渲染消息
    function appendMessage(jsonMsg) {
        $(".messages__content").find(".os-content").append(jsonMsg.data);
        //渲染后处理
        if(jsonMsg.type == "IMAGE"){
            $(".lightbox")[0] && $(".lightbox").lightGallery({enableTouch: !0});
        }

        instance.scroll({ y : "100%" }, 500);
    }

    function sendText(message){
        send(message,"TEXT")
    }

    function sendLabel(message){
        send(message,"LABEL")
    }

    function sendCate(message){
        send(message,"CATE")
    }

    function send(message,type){
        if(!window.WebSocket){return;}
        if(socket.readyState == WebSocket.OPEN){
            var json = {};
            json.type = type;
            json.mode = mode;
            json.id = id;
            json.message = modeMessage;
            if(type=='LABEL' || type=='CATE'){
                json.data = message.data;

                var json0 = {};
                json0.type = type;
                json0.data = message.id;
                socket.send(JSON.stringify(json0));
            }else{
                json.data = message;
                socket.send(JSON.stringify(json));
            }
            appendRightMessage(JSON.stringify(json));
        }else{
            alert("WebSocket 连接没有建立成功！");
        }
    }

    $(document).on("click",".queryQuestion",function () {
        var obj = {};
        obj.id = $(this).attr("data-id");
        obj.data = $(this).attr("data");
        sendLabel(obj);
    });

    $(document).on("click",".questionLink",function () {
        send($(this).attr("data"),"TEXT");
    });

    /*$(document).on("click",".jqtree-element",function () {
        var text = $(this).find("span").text();
        var obj = {};
        obj.data = text;
        for(var i=0;i<cateList.length;i++){
            if(cateList[i].name == text){
                obj.id = parseInt(cateList[i].id)+"";
            }
        }
        sendCate(obj);
    });*/

    //加载常见问题列表
    loadQuestionList();
    function loadQuestionList() {
        $.ajax({
            url: "/question/selectPage",
            type:"get",
            data:{},
            dataType:"json",
            contentType:"application/json",
            success:function (data) {
                var html = "";
                for(var i=0;i<data.length;i++){
                    html+='<a class="listview__item listview__item--active" data="'+data[i].title+'" href="#">'+
                        '<div class="listview__content">'+
                        '<div class="listview__heading">'+data[i].title+'</div>'+
                    '</div>'+
                    '</a>';
                }
                $("#queryList").html(html);
            }
        });
    }


    //加载标签列表
    loadLabelList();
    function loadLabelList() {
        $("#label").html("");
        var val = $("#search").val();
        $.ajax({
            url: "/label/selectList",
            type:"get",
            data:{labelName:val},
            dataType:"json",
            contentType:"application/json",
            success:function (data) {
                var html = "";
                for(var i=0;i<data.length;i++){
                    html+='<a href="#" style="display:none;margin: 3px!important;line-height: 1;" data-id="'+data[i].id+'" data="'+data[i].labelName+'">'+data[i].labelName+'</a>';
                }
                $("#label").html(html);

                var animation = 'slideInUp';
                var label = $("#label").find('a');
                var animationDuration = 0;
                /*if (animation === "hinge") {
                    animationDuration = 1000;
                }else {
                    animationDuration = 300;
                }*/

                label.removeAttr('class');
                label.each(function (i,o) {
                    $(o).addClass('queryQuestion btn btn-theme animated ');
                    setTimeout(function(){
                        $(o).addClass(animation).fadeIn(200);
                        //$(o).removeClass(animation);
                    }, animationDuration);
                    animationDuration += 300;
                });
            }
        });
    }

    //常见问题
    $(document).on("click",".listview__item",function () {
        var text = $(this).attr("data");
        sendText(text);
    });

    $(".nav-link").click(function () {
        var id = $(this).attr("href");
        $(id).siblings(".tab-pane").fadeOut(1);
        $(id).fadeIn(500);
    });

    //加载分类列表
    //loadCategoryList();
    function loadCategoryList() {
        $.ajax({
            url: "/category/selectAll",
            type:"get",
            data:{},
            dataType:"json",
            contentType:"application/json",
            success:function (data) {
                cateList = data;
                treeList = arrayToTree(data,0);
                $(".treeview-expandeds")[0] && $(".treeview-expandeds").tree({
                    data: treeList,
                    autoOpen: !0,
                    closedIcon: $('<i class="zwicon-plus"></i>'),
                    openedIcon: $('<i class="zwicon-minus"></i>')
                });
            }
        });
    }

    function arrayToTree(arr, pid) {
        //  arr 是返回的数据  parendId 父id
        var temp = [];
        var treeArr = arr;
        treeArr.forEach(function(item, index) {
            if (item.pid == pid) {
                if (arrayToTree(treeArr, treeArr[index].id).length > 0) {
                    // 递归调用此函数
                    treeArr[index].children = arrayToTree(treeArr, treeArr[index].id);
                }
                temp.push(treeArr[index]);
            }
        });
        return temp;
    }

    //搜索
    $("#search").on('input', function(){
        var _this = $(this);
        var val = _this.val();
        var type = $(".nav-tabs").find(".active").attr("data-type");
        if(type == 1){//问题
            if(_this.val().length > 1) {
                $.ajax({
                    url: "/question/selectPage",
                    type: "get",
                    data: {title: val},
                    dataType: "json",
                    contentType: "application/json",
                    success: function (data) {
                        var html = "";
                        for (var i = 0; i < data.length; i++) {
                            html += '<a class="listview__item listview__item--active" data="'+data[i].title+'" href="#">' +
                                '<div class="listview__content">' +
                                '<div class="listview__heading">' + data[i].title + '</div>' +
                                '</div>' +
                                '</a>';
                        }
                        $("#queryList").html(html);

                        $('.listview__heading').GL({
                            ocolor:'red',   //设置关键词高亮颜色
                            oshuru: val   //设置要显示的关键词
                        });
                    }
                });
            }else{
                loadQuestionList();
            }
        }else if(type == 2){//分类

        }else if(type == 3){//标签
            loadLabelList();
        }

    });

    $(document).on("click",".card-deck .card",function () {
        $(this).addClass("price-table__item--popular-selected-other").siblings(".card").removeClass("price-table__item--popular-selected-other");
        var type = $(this).attr("data-type");
        $("#button-"+type).click();

        if(type == 'wind'){
            $(".weather #cloud1").css("fill","#efefef33");
            $(".thunder .weather #cloud1").css("fill","#9FA4AD33");
            $(".weather #cloud2").css("fill","#E6E6E633");
            $(".thunder .weather #cloud2").css("fill","#8B8E9833");
            $(".weather #cloud3").css("fill","#D5D5D533");
            $(".thunder .weather #cloud3").css("fill","#7B798833");
        }else{
            $(".weather #cloud1").css("fill","#efefef03");
            $(".thunder .weather #cloud1").css("fill","#9FA4AD03");
            $(".weather #cloud2").css("fill","#E6E6E603");
            $(".thunder .weather #cloud2").css("fill","#8B8E9803");
            $(".weather #cloud3").css("fill","#D5D5D503");
            $(".thunder .weather #cloud3").css("fill","#7B798803");
        }
    });

    function getFormatDate() {
        var date = new Date();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentDate = date.getFullYear() + "-" + month + "-" + strDate
            + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        return currentDate;
    }
});

