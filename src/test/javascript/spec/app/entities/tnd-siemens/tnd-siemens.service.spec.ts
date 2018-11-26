/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TndSiemensService } from 'app/entities/tnd-siemens/tnd-siemens.service';
import { ITndSiemens, TndSiemens } from 'app/shared/model/tnd-siemens.model';

describe('Service Tests', () => {
    describe('TndSiemens Service', () => {
        let injector: TestBed;
        let service: TndSiemensService;
        let httpMock: HttpTestingController;
        let elemDefault: ITndSiemens;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(TndSiemensService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new TndSiemens(
                0,
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                currentDate
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        tndWarten: currentDate.format(DATE_FORMAT),
                        tndDslFrist: currentDate.format(DATE_FORMAT)
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

            it('should create a TndSiemens', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        tndWarten: currentDate.format(DATE_FORMAT),
                        tndDslFrist: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        tndWarten: currentDate,
                        tndDslFrist: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new TndSiemens(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a TndSiemens', async () => {
                const returnedFromService = Object.assign(
                    {
                        tndObjNr: 'BBBBBB',
                        tndTeilObjNr: 'BBBBBB',
                        tndWarten: currentDate.format(DATE_FORMAT),
                        tndLeitungsNrAlt: 'BBBBBB',
                        tndLeitungsNrNeu: 'BBBBBB',
                        tndGSMNr: 'BBBBBB',
                        tndGSMNr2: 'BBBBBB',
                        tndWartungBMA: 'BBBBBB',
                        tndSiSe: 'BBBBBB',
                        tndLogin: 'BBBBBB',
                        tndPasswort: 'BBBBBB',
                        tndIP: 'BBBBBB',
                        tndTelekomNr: 1,
                        tndLeitungsNrPseudo: 1,
                        tndDslFrist: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        tndWarten: currentDate,
                        tndDslFrist: currentDate
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

            it('should return a list of TndSiemens', async () => {
                const returnedFromService = Object.assign(
                    {
                        tndObjNr: 'BBBBBB',
                        tndTeilObjNr: 'BBBBBB',
                        tndWarten: currentDate.format(DATE_FORMAT),
                        tndLeitungsNrAlt: 'BBBBBB',
                        tndLeitungsNrNeu: 'BBBBBB',
                        tndGSMNr: 'BBBBBB',
                        tndGSMNr2: 'BBBBBB',
                        tndWartungBMA: 'BBBBBB',
                        tndSiSe: 'BBBBBB',
                        tndLogin: 'BBBBBB',
                        tndPasswort: 'BBBBBB',
                        tndIP: 'BBBBBB',
                        tndTelekomNr: 1,
                        tndLeitungsNrPseudo: 1,
                        tndDslFrist: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        tndWarten: currentDate,
                        tndDslFrist: currentDate
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

            it('should delete a TndSiemens', async () => {
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
