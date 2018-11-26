/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AbnahmeprotokollService } from 'app/entities/abnahmeprotokoll/abnahmeprotokoll.service';
import { IAbnahmeprotokoll, Abnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';

describe('Service Tests', () => {
    describe('Abnahmeprotokoll Service', () => {
        let injector: TestBed;
        let service: AbnahmeprotokollService;
        let httpMock: HttpTestingController;
        let elemDefault: IAbnahmeprotokoll;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AbnahmeprotokollService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Abnahmeprotokoll(
                0,
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                'image/png',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        datumLeistung: currentDate.format(DATE_FORMAT),
                        datum: currentDate.format(DATE_FORMAT)
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

            it('should create a Abnahmeprotokoll', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        datumLeistung: currentDate.format(DATE_FORMAT),
                        datum: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datumLeistung: currentDate,
                        datum: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Abnahmeprotokoll(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Abnahmeprotokoll', async () => {
                const returnedFromService = Object.assign(
                    {
                        objektNummer: 'BBBBBB',
                        datumLeistung: currentDate.format(DATE_FORMAT),
                        firma: 'BBBBBB',
                        kundeAsp: 'BBBBBB',
                        firmaAsp: 'BBBBBB',
                        sise: true,
                        zusatzleistungen: 'BBBBBB',
                        antenne: 'BBBBBB',
                        gprsSignal: 'BBBBBB',
                        alarmTest: true,
                        alarmBemerkung: 'BBBBBB',
                        ort: 'BBBBBB',
                        datum: currentDate.format(DATE_FORMAT),
                        abnProtokoll: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        datumLeistung: currentDate,
                        datum: currentDate
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

            it('should return a list of Abnahmeprotokoll', async () => {
                const returnedFromService = Object.assign(
                    {
                        objektNummer: 'BBBBBB',
                        datumLeistung: currentDate.format(DATE_FORMAT),
                        firma: 'BBBBBB',
                        kundeAsp: 'BBBBBB',
                        firmaAsp: 'BBBBBB',
                        sise: true,
                        zusatzleistungen: 'BBBBBB',
                        antenne: 'BBBBBB',
                        gprsSignal: 'BBBBBB',
                        alarmTest: true,
                        alarmBemerkung: 'BBBBBB',
                        ort: 'BBBBBB',
                        datum: currentDate.format(DATE_FORMAT),
                        abnProtokoll: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datumLeistung: currentDate,
                        datum: currentDate
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

            it('should delete a Abnahmeprotokoll', async () => {
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
