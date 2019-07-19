function add(){
    var pageNumbers=$("#pageNumber").val();
    var pageSizes=$("#pageSize").val();
    var page=Number(pageNumbers)+Number(pageSizes);
    var pageSum=$("#pageSum").val();
    if(Number(page)>Number(pageSum)){
        page=pageNumbers;
    }
    $("#pageNumber").val(Number(page));
    query();
}
function adda(){
    var pageNumbers=$("#pageNumber").val();
    var pageSizes=$("#pageSize").val();
    var page=Number(pageNumbers)+Number(pageSizes)+Number(pageSizes);
    var pageSum=$("#pageSum").val();
    if(Number(page)>Number(pageSum)){
        page=pageNumbers;
    }
    $("#pageNumber").val(Number(page));
    query();
}
function edde(){
    var pageNumbers=$("#pageNumber").val();
    var pageSizes=$("#pageSize").val();
    var page=Number(pageNumbers)-Number(pageSizes)-Number(pageSizes);
    var pageSum=$("#pageSum").val();
    if(Number(page)>Number(pageSum)){
        page=pageNumbers;
    }
    $("#pageNumber").val(Number(page));
    query();
}
function edd(){
    var pageNumbers=$("#pageNumber").val();
    var pageSizes=$("#pageSize").val();
    var page=Number(pageNumbers)-Number(pageSizes);
    if(Number(page)<=0){
        page=0;
    }
    $("#pageNumber").val(Number(page));
    query();
}
function heads(){
    var page=0;
    $("#pageNumber").val(Number(page));
    query();
}
function end(){
    var pageSizes=$("#pageSize").val();
    var pageSum=$("#pageSum").val();
    var pageEnd=Number(pageSum)%Number(pageSizes);
    var page=Number(pageSum)-Number(pageEnd);
    if(Number(pageEnd)==0){
        if(Number(pageSum)<=Number(pageSizes)){
            page=0;
        }else{
            page=Number(pageSum)-Number(pageSizes)
        }
    }
    $("#pageNumber").val(Number(page));
    query();
}
function pageAjax(pageOrd,pageNumber,pageWin,pageSum,pageSize,pageCon,page){
    var pageBeforBf=Number(pageOrd)-Number(1);
    var pageBefor=Number(pageOrd);
    var pageThe=Number(pageOrd)+Number(1);
    var pageNext=Number(pageOrd)+Number(2);
    var pageNextNt=Number(pageOrd)+Number(3);
    $("#"+page).append("<h4>第"+pageThe+"页    共"+Number(pageCon)+"页</h4>");
    $("#"+page).append("<button style='width:60px;height:30px;' onclick='heads()'>首页</button>");
    if(pageNumber>0){
        $("#"+page).append("<button style='width:80px;height:30px;' onclick='edd()'>上一页</button>");
    }else{
        $("#"+page).append("<button style='width:80px;height:30px;'>上一页</button>");
    }
    if(pageThe>2){
        $("#"+page).append("<button style='width:60px;height:30px;' onclick='edde()'>"+pageBeforBf+"</button>")
    }
    if(pageThe>1){
        $("#"+page).append("<button style='width:60px;height:30px;' onclick='edd()'>"+pageBefor+"</button>")
    }
    $("#"+page).append("<button style='width:60px;height:30px;'>"+pageThe+"</button>")
    if(pageWin>1){
        $("#"+page).append("<button style='width:60px;height:30px;' onclick='add()'>"+pageNext+"</button>");
    }
    if(pageWin>2){
        $("#"+page).append("<button style='width:60px;height:30px;' onclick='adda()'>"+pageNextNt+"</button>");
    }
    if(Number(pageSum)-(Number(pageNumber))>Number(pageSize)){
        $("#"+page).append("<button style='width:80px;height:30px;' onclick='add()'>下一页</button>");
    }else{
        $("#"+page).append("<button style='width:80px;height:30px;'>下一页</button>");
    }
    $("#"+page).append("<button style='width:60px;height:30px;' onclick='end()'>尾页</button>");
}