package mju.com.station;

import android.content.Context;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

public class ReadAmenitiesExcel {
    Context ct;
    int result = 0;
    ReadAmenitiesExcel(Context baseContext){
        ct = baseContext;
    }

    public void readAmenitiesExcel(){
        StationAmenities stationAmenities [];
        try{
            //파일읽기
            InputStream is = ct.getResources().getAssets().open("amenities.xls");
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

                    stationAmenities = new StationAmenities[rowTotal];

                    for(int row = rowIndexStart; row<rowTotal; row++){      //한 줄씩

                        String current = "";    //현재역
                        String previous = "";   //이전역
                        String next = "";       //다음역
                        int line = 0;           //호선
                        int stairs = 0;       //계단 수
                        String vendingMachine = "";     //자판기 유무
                        String airConditioner = ""; //냉방기
                        String toilet = "";     //화장실 위치
                        String getOffQuick = "";    //빠른 하차 위치
                        String congestion = "";     //혼잡도

                        //col: 컬럼순서, contents: 데이터값
                        for(int col=0; col < colTotal; col++) {      //열
                            String contents = sheet.getCell(col, row).getContents();    //행은 고정, 열 값을 ++하면서 contents를 뽑아냄.
                            //System.out.println("테스트 - " + (row+1) + "번째 줄의 " + (col + 1) + "번째 칸: " + contents);
                            switch (col) {
                                case 0:         //cellIndex(몇번째열인지)가 0이면 현재역
                                    current = contents;
                                    continue;
                                case 1:         //cellIndex(몇번째열인지)가 1이면 이전역
                                    previous = contents;
                                    continue;
                                case 2:         //cellIndex(몇번째열인지)가 2이면 다음역
                                    next = contents;
                                    continue;
                                case 3:         //cellIndex(몇번째열인지)가 3이면 몇 호선인지(라인)
                                    line = getStrToInt(contents);
                                    continue;
                                case 4:         //cellIndex(몇번째열인지)가 4이면 계단 수
                                    stairs = getStrToInt(contents);
                                    continue;
                                case 5:         //cellIndex(몇번째열인지)가 5이면 자판기유무
                                    vendingMachine = contents;
                                    continue;
                                case 6:         //cellIndex(몇번째열인지)가 6이면 냉방기 정보
                                    airConditioner = contents;
                                    continue;
                                case 7:         //cellIndex(몇번째열인지)가 7이면 화장실 위치
                                    toilet = contents;
                                    continue;
                                case 8:         //cellIndex(몇번째열인지)가 8이면 빠른하차 위치
                                    getOffQuick = contents;
                                    continue;
                                case 9:         //cellIndex(몇번째열인지)가 9이면 혼잡도
                                    congestion = contents;
                                    continue;
                            }
                        }
                        //클래스 배열에 모든 편의시설 정보 저장
                        stationAmenities[row-1] = new StationAmenities(current, previous, next, line, stairs, vendingMachine,
                                airConditioner, toilet, getOffQuick, congestion);
                    }
                    //result 변수에 우리가 찾는 역의 배열 인덱스 값을 찾아서 저장.
                    int result = findStation(stationAmenities, rowTotal);
                    //결과 출력
                    resultPrint(stationAmenities, result);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    } //readExcel()

    //String을 int형으로 변환
    public int getStrToInt(String str){
        return Integer.parseInt(str);
    }


    public int findStation(StationAmenities stationAmenities[], int rowTotal){

        for(int count = 0; count<rowTotal-1; count++){
            if(stationAmenities[count].getCurrentName().equals("101")){     //현재역 찾기
                if(stationAmenities[count].getLine()==1){                   //라인(호선) 찾기
                    //
                    System.out.println("해당 역을 찾았습니다! 해당역의 정보를 불러오겠습니다.");
                    //System.out.println("해당 역의 정보는 "+(count+2)+"번 줄에 있습니다.");
                    return count;
                }
            }
        }
        
        return 10000;       //없을 경우 일부러 오류발생
    }


    public void resultPrint(StationAmenities stationAmenities[], int count){
        //결과를 출력해주는 함수
        System.out.println("현재 역은 "+stationAmenities[count].getCurrentName()+"역입니다.");
        System.out.println("이전 역은 "+stationAmenities[count].getPreviousName()+"역입니다.");
        System.out.println("다음 역은 "+stationAmenities[count].getNextName()+"역입니다.");
        System.out.println("현재 역은 "+stationAmenities[count].getLine()+"호선입니다.");
        System.out.println("현재 역의 계단 수는 "+stationAmenities[count].getStairs()+"개입니다.");
        System.out.println("현재 역에 자판기가 "+stationAmenities[count].getVendingMachine());
        System.out.println("현재 역의 냉방칸은 "+stationAmenities[count].getAirConditioner()+"입니다.");
        System.out.println("현재 역의 화장실 위치는 "+stationAmenities[count].getToilet()+"입니다.");
        System.out.println("현재 역의 빠른 하차 위치는 "+stationAmenities[count].getGetOffQuick()+"칸입니다.");
        System.out.println("현재 역의 혼잡도는 "+stationAmenities[count].getCongestion());

    }
}
