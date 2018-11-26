/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { TblVertragService } from 'app/entities/tbl-vertrag/tbl-vertrag.service';
import { ITblVertrag, TblVertrag } from 'app/shared/model/tbl-vertrag.model';

describe('Service Tests', () => {
    describe('TblVertrag Service', () => {
        let injector: TestBed;
        let service: TblVertragService;
        let httpMock: HttpTestingController;
        let elemDefault: ITblVertrag;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(TblVertragService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new TblVertrag(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                false
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a TblVertrag', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new TblVertrag(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a TblVertrag', async () => {
                const returnedFromService = Object.assign(
                    {
                        verHv: 'BBBBBB',
                        verEquipmentNr: 'BBBBBB',
                        verObjektNr: 'BBBBBB',
                        verTeilmelder: 'BBBBBB',
                        verVpName: 'BBBBBB',
                        verVpName2: 'BBBBBB',
                        verVpStrasse: 'BBBBBB',
                        verVpPlz: 'BBBBBB',
                        verVpOrt: 'BBBBBB',
                        verObjName: 'BBBBBB',
                        verObjStrasse: 'BBBBBB',
                        verObjPlz: 'BBBBBB',
                        verObjOrt: 'BBBBBB',
                        verSiseVertrag: 'BBBBBB',
                        verSiseTyp: 'BBBBBB',
                        verKonzId: 'BBBBBB',
                        verWartung: 'BBBBBB',
                        verMarkieren: true,
                        verDummy: true
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of TblVertrag', async () => {
                const returnedFromService = Object.assign(
                    {
                        verHv: 'BBBBBB',
                        verEquipmentNr: 'BBBBBB',
                        verObjektNr: 'BBBBBB',
                        verTeilmelder: 'BBBBBB',
                        verVpName: 'BBBBBB',
                        verVpName2: 'BBBBBB',
                        verVpStrasse: 'BBBBBB',
                        verVpPlz: 'BBBBBB',
                        verVpOrt: 'BBBBBB',
                        verObjName: 'BBBBBB',
                        verObjStrasse: 'BBBBBB',
                        verObjPlz: 'BBBBBB',
                        verObjOrt: 'BBBBBB',
                        verSiseVertrag: 'BBBBBB',
                        verSiseTyp: 'BBBBBB',
                        verKonzId: 'BBBBBB',
                        verWartung: 'BBBBBB',
                        verMarkieren: true,
                        verDummy: true
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
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

            it('should delete a TblVertrag', async () => {
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
