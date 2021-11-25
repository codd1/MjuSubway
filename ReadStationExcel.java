package mju.com.station;

import android.content.Context;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;


public class ReadStationExcel {
    Context ct;
    int result = 0;
    ReadStationExcel(Context baseContext){
        ct = baseContext;
    }

    public void readStationExcel(){
        StationInfo station[];
        try{
            //파일읽기
            InputStream is = ct.getResources().getAssets().open("stations.xls");
            //엑셀파일
            Workbook wb = Workbook.getWorkbook(is);

            //엑셀파일이 있다면
            if(wb != null){
                Sheet sheet = wb.getSheet(0);   //시트 불러오기
                if(sheet != null){
                    //1행 1열   1행 2열
                    //2행 1열   2행 2열
                    int colTotal = sheet.getColumns();  //전체 컬럼
                    int rowIndexStart = 1;  //행 시작 인덱스 번호, 행을 세는 변수
                    int rowTotal = sheet.getColumn(colTotal-1).length;

                    station = new StationInfo[rowTotal];

                    for(int row = rowIndexStart; row<rowTotal; row++){      //한 줄씩

                        String key = "";    //출발역
                        String dest = "";   //도착역
                        int time = 0;       //소요시간
                        int dist = 0;       //소요거리
                        int money = 0;      //소요비용

                        //col: 컬럼순서, contents: 데이터값
                        for(int col=0; col < colTotal; col++) {      //열
                            String contents = sheet.getCell(col, row).getContents();    //행은 고정, 열 값을 ++하면서 contents를 뽑아냄.
                            //System.out.println("테스트 - " + (row+1) + "번째 줄의 " + (col + 1) + "번째 칸: " + contents);
                            switch (col) {
                                case 0:         //cellIndex(몇번째열인지)가 0이면 출발역
                                    key = contents;
                                    continue;
                                case 1:         //cellIndex(몇번째열인지)가 1이면 도착역
                                    dest = contents;
                                    continue;
                                case 2:         //cellIndex(몇번째열인지)가 2이면 소요시간
                                    time = getStrToInt(contents);
                                    continue;

                                case 3:         //cellIndex(몇번째열인지)가 3이면 소요거리
                                    dist = getStrToInt(contents);
                                    continue;
                                case 4:         //cellIndex(몇번째열인지)가 4이면 소요비용
                                    money = getStrToInt(contents);
                            }
                        }
                        station[row-1] = new StationInfo(key, dest, time, dist, money);
                    }
                    result = findStation(station, rowTotal);
                    resultPrint(station, result);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    } //readExcel()


    public int getStrToInt(String str){
        return Integer.parseInt(str);
    }

    public int findStation(StationInfo station[], int rowTotal){

        //역을 찾는 함수.
        //equals(" ")부분에 찾길 원하는 역을 변수로 넣으면 된다.
        for(int count = 0; count < rowTotal-1; count++){
            if(station[count].getStartName().equals("123")){        //출발지 찾기
                if(station[count].getDestName().equals("305")){     //도착지 찾기
                    return count;
                }
            }
        }
        return 10000;       //없을경우 오류발생
    }

    public void resultPrint(StationInfo station[], int index){
        //결과를 출력해주는 함수
        System.out.println("출발역은 "+station[index].getStartName()+"역입니다.");
        System.out.println("도착역은 "+station[index].getDestName()+"역입니다.");
        System.out.println("소요시간은 "+station[index].getTime()+"초입니다.");
        System.out.println("소요거리는 "+station[index].getDist()+"m입니다.");
        System.out.println("소요비용은 "+station[index].getPrice()+"원입니다.");
    }
}
