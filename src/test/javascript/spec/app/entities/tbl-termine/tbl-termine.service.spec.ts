/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TblTermineService } from 'app/entities/tbl-termine/tbl-termine.service';
import { ITblTermine, TblTermine } from 'app/shared/model/tbl-termine.model';

describe('Service Tests', () => {
    describe('TblTermine Service', () => {
        let injector: TestBed;
        let service: TblTermineService;
        let httpMock: HttpTestingController;
        let elemDefault: ITblTermine;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(TblTermineService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new TblTermine(
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                false,
                false,
                false,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                false,
                false,
                false,
                currentDate,
                'AAAAAAA',
                false,
                false,
                false,
                false,
                false
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        terIbsDatum: currentDate.format(DATE_FORMAT),
                        terDatumNeu: currentDate.format(DATE_FORMAT),
                        terDslFrist: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a TblTermine', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        terIbsDatum: currentDate.format(DATE_FORMAT),
                        terDatumNeu: currentDate.format(DATE_FORMAT),
                        terDslFrist: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        terIbsDatum: currentDate,
                        terDatumNeu: currentDate,
                        terDslFrist: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new TblTermine(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a TblTermine', async () => {
                const returnedFromService = Object.assign(
                    {
                        terTermianId: 1,
                        terObjektNr: 'BBBBBB',
                        terPerNrRef: 'BBBBBB',
                        terIbsDatum: currentDate.format(DATE_FORMAT),
                        terIbsUhrzeit: 'BBBBBB',
                        terDatumNeu: currentDate.format(DATE_FORMAT),
                        terBemerkung: 'BBBBBB',
                        terAntennen: true,
                        terMontiert: true,
                        terIbsProvisorium: true,
                        terKonzId: 'BBBBBB',
                        terStatus: 'BBBBBB',
                        terStatusProtokoll: 'BBBBBB',
                        terKundenInfoTyp: 'BBBBBB',
                        terKundeInformiert: true,
                        terKundeBestaetigt: true,
                        terKundeAntenneErhalten: true,
                        terSiemensInformiert: true,
                        terDslFrist: currentDate.format(DATE_FORMAT),
                        terTermintyp: 'BBBBBB',
                        terFunkmessung: true,
                        terKlaerung: true,
                        terAbgesagt: true,
                        terHinweis: true,
                        terSimImGeraet: true
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        terIbsDatum: currentDate,
                        terDatumNeu: currentDate,
                        terDslFrist: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of TblTermine', async () => {
                const returnedFromService = Object.assign(
                    {
                        terTermianId: 1,
                        terObjektNr: 'BBBBBB',
                        terPerNrRef: 'BBBBBB',
                        terIbsDatum: currentDate.format(DATE_FORMAT),
                        terIbsUhrzeit: 'BBBBBB',
                        terDatumNeu: currentDate.format(DATE_FORMAT),
                        terBemerkung: 'BBBBBB',
                        terAntennen: true,
                        terMontiert: true,
                        terIbsProvisorium: true,
                        terKonzId: 'BBBBBB',
                        terStatus: 'BBBBBB',
                        terStatusProtokoll: 'BBBBBB',
                        terKundenInfoTyp: 'BBBBBB',
                        terKundeInformiert: true,
                        terKundeBestaetigt: true,
                        terKundeAntenneErhalten: true,
                        terSiemensInformiert: true,
                        terDslFrist: currentDate.format(DATE_FORMAT),
                        terTermintyp: 'BBBBBB',
                        terFunkmessung: true,
                        terKlaerung: true,
                        terAbgesagt: true,
                        terHinweis: true,
                        terSimImGeraet: true
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        terIbsDatum: currentDate,
                        terDatumNeu: currentDate,
                        terDslFrist: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a TblTermine', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
